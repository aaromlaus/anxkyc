package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anx.kyc.common.UserLevelType;
import com.anx.kyc.model.AnxUser;
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
	public String updateUserLevel(@RequestParam("userId") int userId, @RequestParam("status") String status, Map<String, Object> model) {
		AnxUser user = userService.getUserById(userId);
		//userService.findLevelUserByIdAndLevel(userId,user.getUserLevel())
		if(status.equalsIgnoreCase("approve")) {
			user.setUserLevel(userService.getUserLevel(UserLevelType.LEVEL_2));
			
		} else if (status.equalsIgnoreCase("reject")) {
			user.setUserLevel(userService.getUserLevel(UserLevelType.LEVEL_1));
		}
		userService.saveUser(user,false);
		return "redirect:/administrator/";
	}

}
