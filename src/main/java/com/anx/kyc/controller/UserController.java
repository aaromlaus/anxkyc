package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.UserService;

@Controller
@RequestMapping("/profile")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/main")
	public String mainPage(Map<String, Object> model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser anxUser = userService.findAnxUserByUsername(currentPrincipalName);
		anxUser.setLevelUser(userService.findLevelUserById(anxUser));
		model.put("anxUser", anxUser);
		return "main/user";
	}
}
