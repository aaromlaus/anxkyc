package com.anx.kyc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.anx.kyc.common.AlertStyleMessages;
import com.anx.kyc.common.VerificationStatusType;
import com.anx.kyc.common.VerificationType;
import com.anx.kyc.helper.AnxMessageHelper;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.PhoneCode;
import com.anx.kyc.service.UserService;
import com.anx.kyc.service.UserVerificationService;
import com.anx.kyc.util.AnxUtil;
import com.anx.kyc.validator.RegistrationFormValidator;

@Controller
@RequestMapping(value="${url.signup}")
@SessionAttributes
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserVerificationService userVerService;

	@Autowired
	private RegistrationFormValidator rfValidator;

	@Autowired
	private AnxMessageHelper amHelper;

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
		for(FieldError fe : result.getFieldErrors()) {
			model.put("emailDisplay", fe.getField().equalsIgnoreCase("phoneNumber") ? "none" : "block");
			model.put("phoneDisplay", fe.getField().equalsIgnoreCase("phoneNumber") ? "block" : "none");
		}
		
		if (result.hasErrors()) {
			model.put("msgCss", AlertStyleMessages.DANGER.getValue());
			model.put("msgDetails", amHelper.get("registration.error"));			
			model.put("anxUserForm", anxUser);
			return "registration/anxaccount";
		}

		return "registration/anxuserdetails";
	}

	@RequestMapping(value = "/save")
	public String saveUserDetails(@ModelAttribute("anxUserForm") AnxUser anxUser, Map<String, Object> model, HttpSession session, 
			HttpServletRequest request) {
		
		populate(model, anxUser, session);
		String successMessage = amHelper.get("registration.mobile.success");
		String verificationCode = userService.generateandSetVerificationCode(anxUser);
		userService.saveUserDetails(anxUser);
		userVerService.addUserVerification(anxUser.getUserId());
		
		if(AnxUtil.isNotNullOrEmpty(anxUser.getEmailAddress())) {
			userService.prepareAndSendUserRegistrationEmail(anxUser, verificationCode, request);
			successMessage = amHelper.get("registration.email.success");
		} else if(AnxUtil.isNotNullOrEmpty(anxUser.getPhoneNumber())) {
			userVerService.updateVerificationStatus(anxUser, VerificationType.PHONE_VERIFICATION.name(), VerificationStatusType.COMPLETED);
		}
		
		model.put("msgCss", AlertStyleMessages.SUCCESS.getValue());
		model.put("msgDetails", successMessage);
		
		return "registration/accountregistrationsuccess";
	}
	
	@RequestMapping(value = "/verify")
	public String verifyEmail(@RequestParam("details") String verificationCode, HttpServletRequest request, Map<String, Object> model) {
		AnxUser user = userService.verifyAndActivateUser(verificationCode);
		String emailConfirmMsg = amHelper.get("registration.email.verify.new");
		if(user == null) {
			emailConfirmMsg = amHelper.get("registration.email.verify.existing");
		} 
		
		model.put("emailConfirmMsg", emailConfirmMsg);
		return "registration/emailconfirmationsuccess";
	}
	
	
}
