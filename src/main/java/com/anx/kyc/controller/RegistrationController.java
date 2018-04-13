package com.anx.kyc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.common.AlertStyleMessages;
import com.anx.kyc.common.AnxMessageService;
import com.anx.kyc.common.RoleType;
import com.anx.kyc.common.UserLevelType;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.PhoneCode;
import com.anx.kyc.service.UserService;
import com.anx.kyc.validator.RegistrationFormValidator;

@Controller
@RequestMapping("/signup")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private RegistrationFormValidator rfValidator;

	@Autowired
	private AnxMessageService amService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(rfValidator);
	}

	@RequestMapping("/")
	public String signup(Map<String, Object> model, HttpSession session) {
		populate(model, null, session);
		return "registration/anxaccount";
	}

	private void populate(Map<String, Object> model, AnxUser user, HttpSession session) {
		model.put("anxUserForm", user == null? new AnxUser() : user);
		List<PhoneCode> countryCodeList = userService.getAllPhoneCode();
		model.put("countryCodeList", countryCodeList);
		session.setAttribute("countryCodeList", countryCodeList);
	}

	@RequestMapping(value = "/createaccount")
	public String createAccount(@ModelAttribute("anxUserForm") AnxUser anxUser, BindingResult result,
			Map<String, Object> model, HttpSession session) {
		
		populate(model, anxUser, session);

		rfValidator.validate(anxUser, result);
		if (result.hasErrors()) {
			model.put("msgCss", AlertStyleMessages.DANGER.getValue());
			model.put("msgDetails", amService.get("registration.error"));			
			model.put("anxUserForm", anxUser);
			return "registration/anxaccount";
		}

		return "registration/anxuserdetails";
	}

	@RequestMapping(value = "/save")
	public String saveUserDetails(@ModelAttribute("anxUserForm") AnxUser anxUser, Map<String, Object> model, HttpSession session) {

		populate(model, anxUser, session);
		anxUser.setRole(userService.getRole(RoleType.USER));
		anxUser.setUserLevel(userService.getUserLevel(UserLevelType.LEVEL_1));
		if (null != anxUser && null != anxUser.getPhoneCode() && null != anxUser.getPhoneCode().getPhoneCodeId()) {
			anxUser.setPhoneCode(userService.findPhoneCodeById(anxUser.getPhoneCode().getPhoneCodeId()));
		}
		userService.saveUser(anxUser);
		userService.saveNewLevelUser(anxUser);
		
		model.put("msgCss", AlertStyleMessages.SUCCESS.getValue());
		model.put("msgDetails", amService.get("registration.success"));
		return "registration/anxuserdetails";
	}

}
