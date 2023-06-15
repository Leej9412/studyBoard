package kr.or.ddit.security.auth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.security.GroupAuthority;
import kr.or.ddit.security.auth.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Inject
	private MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = Optional.ofNullable(memberDAO.selectMember(username))
									.orElseThrow(()->{return new UsernameNotFoundException("");}); 
		
		Set<GrantedAuthority> authSet = new LinkedHashSet<>();
		authSet.addAll(loadAuthoritiesByUsername(username));
		authSet.addAll(loadGroupAuthoritiesByUsername(username));
		
		return new MemberVOWrapper(member, authSet);
	}

	private List<GrantedAuthority> loadAuthoritiesByUsername(String username){
		String[] mockRoles = new String[] {"ROLE_MOCK1", "ROLE_MOCK2"};
		return Stream.of(mockRoles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	private List<GrantedAuthority> loadGroupAuthoritiesByUsername(String username){
		List<GrantedAuthority> authorities = new ArrayList<>();
		if("a001".equals(username)) {
			authorities.addAll(
				Arrays.asList(
					new SimpleGrantedAuthority("GROUP1")
					, new SimpleGrantedAuthority("ROLE_GROUP_MANAGER")
					, new GroupAuthority("GROUP1", "GROUP_MANAGER")
				)
			);
		}else if("b001".equals(username)) {
			authorities.addAll(
				Arrays.asList(
					new SimpleGrantedAuthority("GROUP1")
					, new SimpleGrantedAuthority("ROLE_GROUP_NORMAL")
					, new GroupAuthority("GROUP1", "GROUP_NORMAL")
				)
			);
		}else if("c001".equals(username)) {
			authorities.addAll(
				Arrays.asList(
					new SimpleGrantedAuthority("GROUP1")
					, new SimpleGrantedAuthority("GROUP2")
					, new SimpleGrantedAuthority("ROLE_GROUP_NORMAL")
					, new SimpleGrantedAuthority("ROLE_GROUP_MANAGER")
					, new GroupAuthority("GROUP1", "GROUP_NORMAL")
					, new GroupAuthority("GROUP2", "GROUP_MANAGER")
				)
			);
		}else {
			authorities.addAll(
				Arrays.asList(
					new SimpleGrantedAuthority("GROUP2")
					, new SimpleGrantedAuthority("ROLE_GROUP_NORMAL")
					, new GroupAuthority("GROUP2", "GROUP_NORMAL")
				)
			);
		}
		
		return authorities;
	}
}
