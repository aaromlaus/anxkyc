package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anx.kyc.common.UserLevelType;
import com.anx.kyc.service.UserService;

@Controller
@RequestMapping("/administrator")
public class AdminDashboardController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/")
	public String administrator(Map<String, Object> model) {
		model.put("userList", userService.getUsersByUserLevelName(UserLevelType.LEVEL_2_PENDING));
		return "admin/admindashboard";
	}
	
	@RequestMapping(value="/updateUserLevel")
	public String updateUserLevel(@RequestParam("userId") String userId, @RequestParam("status") String status, Map<String, Object> model) {
		userService.updateAnxUserLevel(userId, status);
		return "redirect:/administrator/";
	}

}
