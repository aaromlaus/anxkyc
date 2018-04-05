package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.model.Login;

@Controller
public class LoginController {
	
		@Value("${welcome.message:test}")
		private String message = "Hello World";
		
		@RequestMapping("/")
		public String welcome(Map<String, Object> model) {
			model.put("message", this.message);
			return "welcome";
		}
		
		@RequestMapping("/login")
		public String loginPage(Map<String, Object> model) {
			model.put("message", this.message);
			model.put("loginForm", new Login());
			return "login";
		}
		
		@RequestMapping(value="/doLogin")
		public String doLogin(@ModelAttribute("loginForm") Login login) {
			return "welcome";
		}
}
