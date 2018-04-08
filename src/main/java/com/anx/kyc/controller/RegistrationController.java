package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.common.AnxConstant;
import com.anx.kyc.model.AnxUser;

@Controller
@RequestMapping("/signup")
public class RegistrationController {
	
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
		model.put("anxUserForm", anxUser);
		return "registration/anxuserdetails";
	}

}
