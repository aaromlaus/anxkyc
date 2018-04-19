package com.anx.kyc.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class ResetPasswordInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		List<String> urls = Arrays.asList(new String[]{ "/verifyCode", "/resetPassword", "/resetSuccess"});
		if(urls.contains(requestURI) ) {
			if(request.getServletContext().getAttribute("username") != null) {
				return true;
			}else {
				response.sendRedirect("/forgotPassword");
			}
		}
		return true;
	}

}
