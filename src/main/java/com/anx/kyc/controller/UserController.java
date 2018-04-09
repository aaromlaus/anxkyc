package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.UserRegistrationService;

@Controller
@RequestMapping("/profile")
public class UserController {
	@Autowired
	private UserRegistrationService urService;
	@RequestMapping(value="/main")
	public String mainPage(Map<String, Object> model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser anxUser = urService.findAnxUserByUsername(currentPrincipalName);
		anxUser.setLevelUser(urService.findLevelUserById(anxUser));
		model.put("anxUser", anxUser);
		return "main/user";
	}
}
