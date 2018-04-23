package com.anx.kyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="${url.identification}")
public class UserIdentificationController {

	@RequestMapping("/")
	public String showIdentityVerificationPage() {
		
		return "main/identification";
	}
	
}
