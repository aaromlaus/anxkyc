package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class UserController {
	
	@RequestMapping(value="/main")
	public String mainPage(Map<String, Object> model) {
		model.put("message", "");
		return "main/user";
	}
}
