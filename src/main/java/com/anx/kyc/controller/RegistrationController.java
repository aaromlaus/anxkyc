package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.common.AlertStyleMessages;
import com.anx.kyc.constant.AnxConstant;
import com.anx.kyc.constant.RoleType;
import com.anx.kyc.constant.UserLevelType;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.UserService;

@Controller
@RequestMapping("/signup")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String signup(Map<String, Object> model) {
		model.put("anxUserForm", new AnxUser());
		return "registration/anxaccount";
	}
	
	@RequestMapping(value="/createaccount")
	public String createAccount(@ModelAttribute("anxUserForm") AnxUser anxUser, Map<String, Object> model) {
		model.put("anxUserForm", anxUser);
		return "registration/anxuserdetails";
	}
	
	
	@RequestMapping(value="/save")
	public String saveUserDetails(@ModelAttribute("anxUserForm") AnxUser anxUser, Map<String, Object> model) {
		
		anxUser.setRole(userService.getRole(RoleType.USER));
		anxUser.setUserLevel(userService.getUserLevel(UserLevelType.LEVEL_1));
		userService.saveUser(anxUser);
		userService.saveNewLevelUser(anxUser);
		
		model.put("msgCss", AlertStyleMessages.SUCCESS.getValue());
		model.put("msgDetails", AnxConstant.REGISTRATION_SUCCESS_MSG);
		return "registration/anxuserdetails";
	}

}
