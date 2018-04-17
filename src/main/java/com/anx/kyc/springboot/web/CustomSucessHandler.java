package com.anx.kyc.springboot.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.anx.kyc.common.AnxSession;

@Component
public class CustomSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private AnxSession anxSession;

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication, request);
		
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication, HttpServletRequest request) {
		String url = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isAdmin(roles)) {
			url = "/administrator/";
		} else if (isUser(roles)) {
			url = "/profile/main/";
		} else {
			url = "/accessDenied";
		}

		anxSession.setHomeUrl(url);
		return "/home";
	}

	@Override
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	@Override
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	private boolean isUser(List<String> roles) {
		for (String role : roles) {
			if (role.equalsIgnoreCase("role_user")) {
				return true;
			}
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		for (String role : roles) {
			if (role.equalsIgnoreCase("role_admin")) {
				return true;
			}
		}
		return false;
	}
	

}
