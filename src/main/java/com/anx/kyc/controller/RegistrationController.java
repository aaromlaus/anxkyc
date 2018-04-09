package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.common.AlertStyleMessages;
import com.anx.kyc.common.AnxConstant;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.UserRegistrationService;

@Controller
@RequestMapping("/signup")
public class RegistrationController {
	
	@Autowired
	private UserRegistrationService urService;
	
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
		
		anxUser.setRole(urService.getRole("user"));
		anxUser.setUserLevel(urService.getUserLevel("level1"));
		urService.saveUser(anxUser);
		urService.saveNewLevelUser(anxUser);
		model.put("msgCss", AlertStyleMessages.SUCCESS.getValue());
		model.put("msgDetails", AnxConstant.REGISTRATION_SUCCESS_MSG);
		return "registration/anxuserdetails";
	}

}
