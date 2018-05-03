package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="${url.identification}")
public class UserIdentificationController {

	@RequestMapping("/")
	public String showIdentityVerificationPage(Map<String, Object> model) {
		return "main/identification";
	}
	
	@RequestMapping("/steps")
	public String showVerificationSteps() {
		return "main/verificationsteps";
	}
	
}
