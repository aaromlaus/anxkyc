package com.anx.kyc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anx.kyc.common.AnxSession;
import com.anx.kyc.model.Login;
import com.anx.kyc.service.EmailService;
import com.anx.kyc.service.UserService;
import com.anx.kyc.util.AnxUtil;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private AnxSession anxSession;

	@Autowired
	private EmailService emailService;

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
		System.out.println("test");
		return "redirect:" + anxSession.getHomeUrl();
	}

	@RequestMapping("/login")
	public String loginPage(Map<String, Object> model, @RequestParam(value = "error", required = false) String error) {
		if (error != null) {
			model.put("errorMsg", "Username or password is incorrect!");
		}
		model.put("loginForm", new Login());
		return "login";
	}

	@RequestMapping(value = "/doLogin")
	public String doLogin(@ModelAttribute("loginForm") Login login) {
		return "welcome";
	}

	@RequestMapping("/forgotPassword")
	public String forgotPassword(Map<String, Object> model, HttpSession session) {
		model.put("loginForm", new Login());
		return "auth/forgotpassword";
	}

	@RequestMapping("/sendCode")
	public String sendCode(@ModelAttribute("loginForm") Login login) {
		if (AnxUtil.isValidEmail(login.getUsername())) {
			emailService.sendVerificationCodeEmail(login.getUsername());
			return "redirect:/login";
		} else {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("errorMsg", "Value is not a valid email address!");
			return "auth/forgotpassword";
		}
	}

	@RequestMapping(value = "/verify")
	public String verifyEmail(@RequestParam("details") String verificationCode, HttpServletRequest request) {
		userService.verifyAndActivateUser(verificationCode);
		return "welcome";
	}

}
