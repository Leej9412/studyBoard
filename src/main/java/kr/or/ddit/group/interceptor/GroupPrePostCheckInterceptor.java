package kr.or.ddit.group.interceptor;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher.MatchResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.vo.MemberVOWrapper;

public class GroupPrePostCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/group/{groupId}/**");
		boolean pass = true;
		if(requestMatcher.matches(request)) {
			// 그룹에 접근 가능한 사용자인지 판단.
			MatchResult matchResult = requestMatcher.matcher(request);
			String groupId = matchResult.getVariables().get("groupId");
			Authentication authencation = (Authentication) request.getUserPrincipal();
			MemberVOWrapper wrapper = (MemberVOWrapper) authencation.getPrincipal();
			pass = wrapper.getAuthorities().stream()
										.filter((ga)->ga.getAuthority().equals(groupId))
										.findAny().isPresent();
										
//			new DefaultRedirectStrategy().sendRedirect(request, response, "/errorPage");
			if(!pass)
				response.sendError(403, MessageFormat.format("{0}의 그룹페이지 접근 권한 없음.", groupId));
		}
		return pass;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
