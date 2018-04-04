package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.repository.AnxUserRepository;

@Controller
public class LoginController {
	// inject via application.properties
		@Value("${welcome.message:test}")
		private String message = "Hello World";
		@Autowired
		private AnxUserRepository anxUserRepository;
		
		@RequestMapping("/")
		public String welcome(Map<String, Object> model) {
			model.put("message", this.message);
			AnxUser anxUser = new AnxUser();
			anxUser.setFirstName("Jeriel");
			anxUser.setLastName("Mercado");
			anxUser.setMiddleName("Bautista");
			anxUserRepository.save(anxUser);
			return "welcome";
		}
}
