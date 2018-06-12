package com.anx.kyc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anx.kyc.common.UserLevelType;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.UserService;

@Controller
@RequestMapping(value="${url.administrator}")
public class AdminDashboardController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/")
	public String administrator(Map<String, Object> model) {
		List<AnxUser> userList = new ArrayList<AnxUser>();
		for(AnxUser lvl2User : userService.getUsersByUserLevelName(UserLevelType.LEVEL_2_PENDING)) {
			userList.add(lvl2User);
		}
		for(AnxUser lvl3User : userService.getUsersByUserLevelName(UserLevelType.LEVEL_3_PENDING)) {
			userList.add(lvl3User);
		}
		model.put("userList", userList);
		return "admin/admindashboard";
	}
	
	@RequestMapping(value="/updateUserLevel")
	public String updateUserLevel(@RequestParam("userId") String userId, @RequestParam("status") String status, Map<String, Object> model) {
		userService.updateAnxUserLevel(userId, status);
		return "redirect:/administrator/";
	}

}
