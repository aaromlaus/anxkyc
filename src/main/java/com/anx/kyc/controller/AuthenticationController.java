package com.anx.kyc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.common.AnxSession;
import com.anx.kyc.model.Login;

@Controller
public class AuthenticationController {

	@Autowired
	private AnxSession anxSession;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "welcome";
	}

	@RequestMapping(value = "/accessDenied")
	public String accessDenied() {
		return "common/accessdenied";
	}

	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request) {
		return "redirect:" + anxSession.getHomeUrl();
	}

	@RequestMapping("/login")
	public String loginPage(Map<String, Object> model) {
		model.put("loginForm", new Login());
		return "login";
	}

	@RequestMapping(value = "/doLogin")
	public String doLogin(@ModelAttribute("loginForm") Login login) {
		return "welcome";
	}

}
