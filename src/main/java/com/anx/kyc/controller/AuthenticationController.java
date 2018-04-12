package com.anx.kyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {
	
	@RequestMapping(value="/accessDenied")
	public String accessDenied() {
		return "common/accessdenied";
	}

}
