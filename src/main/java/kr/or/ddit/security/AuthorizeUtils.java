package kr.or.ddit.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.security.GroupAuthority.EqualLevel;
import kr.or.ddit.security.GroupAuthority.GroupAuthorityComparator;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AuthorizeUtils {
	
	/**
	 * 특정 그룹에 소속됐는지 여부 판단
	 * @param authentication
	 * @param req
	 * @return
	 */
	public static boolean inGroup(Authentication authentication, HttpServletRequest req) {
		AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/group/{groupId}/**");
		boolean pass = false;
		if(requestMatcher.matches(req) && authentication.isAuthenticated()) {
			String groupId = requestMatcher.matcher(req).getVariables().get("groupId");
			
			log.info("authorities : {}, 요청 : {}", authentication.getAuthorities(), groupId);
//			pass = authentication.getAuthorities().stream()
//												.filter((ga)->ga.equals(new SimpleGrantedAuthority(groupId)))
//												.findAny().isPresent();
			pass = authentication.getAuthorities().stream()
					.filter((ga)->{
						EqualLevel level = GroupAuthorityComparator.compareAuthority(ga, new GroupAuthority(groupId, null));
						if(level.compareTo(EqualLevel.EQ_GROUP) >= 0) {
							return true;
						}else {
							return false;
						}
					})
					.findAny().isPresent();
						
		}
		return pass;
	}
	
	/**
	 * 특정 그룹의 특정 role 을 소유했는지 여부 확인
	 * @param authentication
	 * @param req
	 * @param groupRole
	 * @return
	 */
	public static boolean hasGroupRole(Authentication authentication, HttpServletRequest req, String groupRole) {
		boolean pass = inGroup(authentication, req);
		AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/group/{groupId}/**");
		if(pass) {
			String groupId = requestMatcher.matcher(req).getVariables().get("groupId");
			
			log.info("authorities : {}, 요청 : {}", authentication.getAuthorities(), groupId);
//			pass = authentication.getAuthorities().stream()
//					.filter((ga)->ga.equals(new GroupAuthority(groupId, groupRole)))
//					.findAny().isPresent();
			pass = authentication.getAuthorities().stream()
					.filter((ga)->{
						EqualLevel level = GroupAuthorityComparator.compareAuthority(ga, new GroupAuthority(groupId, groupRole));
						return level.compareTo(EqualLevel.EQ_GROUP_ROLE) >= 0;
					})
					.findAny().isPresent();
			
		}
		return pass;
	}

	public static boolean hasAuthorityOnPre(Authentication authentication, int boNo) {
		log.info("현재 사용자 : {}, 조회할려는 게시글 번호: {}", authentication.getName(), boNo);
		return true;
	}
	
	public static boolean hasAuthorityOnPost(Authentication authentication, BoardVO board, String viewName) {
		log.info("현재 사용자 : {}, 게시글 작성자 : {}, 결정된 뷰 정보 : {}", 
				authentication.getName(), board.getBoWriter(), viewName);
		return authentication.getName().equals(board.getBoWriter());
	}
}
