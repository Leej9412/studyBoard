package kr.or.ddit.security.handler;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.SessionFlashMapManager;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{
	
	private final static String DEFAULT_TARGET_URL = "/login";
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private String targetURL;
	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}
	
	@Inject
	private MessageSourceAccessor messageSourceAccessor;
	@Inject
	private LocaleResolver localeResolver;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		Locale locale = localeResolver.resolveLocale(request);
		
		FlashMap flashMap = Optional.ofNullable(RequestContextUtils.getOutputFlashMap(request))
									.orElse(new FlashMap());
		flashMap.put("message", messageSourceAccessor.getMessage("loginError.message", locale));
		new SessionFlashMapManager().saveOutputFlashMap(flashMap, request, response);
		
		redirectStrategy.sendRedirect(request, response, 
								Optional.ofNullable(targetURL)
										.orElse(DEFAULT_TARGET_URL));
	}

}
