package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.constant.UserLevelType;
import com.anx.kyc.service.UserService;

@Controller
@RequestMapping("/administrator")
public class AdminDashboardController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/")
	public String createAccount(Map<String, Object> model) {
		model.put("userList", userService.getUsersByUserLevelName(UserLevelType.LEVEL_2_PENDING));
		return "admin/dashboard";
	}
	

}
