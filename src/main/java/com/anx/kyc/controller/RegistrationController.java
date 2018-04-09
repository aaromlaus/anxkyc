package com.anx.kyc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.common.AnxConstant;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.UserRegistrationService;

@Controller
@RequestMapping("/signup")
public class RegistrationController {
	
	@Autowired
	private UserRegistrationService urService;
	
	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		 dateFormat.setLenient(false);
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		 
	}
	
	@RequestMapping("/")
	public String signup(Map<String, Object> model) {
		model.put("anxUserForm", new AnxUser());
		model.put("privacyAgreement", AnxConstant.PRIVACY_AGREEMENT);
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
		return "registration/anxuserdetails";
	}

}
