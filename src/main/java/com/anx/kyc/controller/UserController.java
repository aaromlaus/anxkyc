package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/user/main")
	public String mainPage(Map<String, Object> model) {
		model.put("message", "");
		return "user/main";
	}
}
