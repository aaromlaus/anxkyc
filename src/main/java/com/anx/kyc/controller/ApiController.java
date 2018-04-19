package com.anx.kyc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anx.kyc.service.EmailService;
import com.anx.kyc.util.AnxUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
	
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/sendCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendCode(@RequestBody String requestBody, HttpSession session) {
		try {
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(requestBody, JsonElement.class);
			JsonObject requestJson = element.getAsJsonObject();
			if (!requestJson.get("emailAddress").isJsonNull()
					&& AnxUtil.isValidEmail(requestJson.get("emailAddress").getAsString())) {
				int code = emailService.sendVerificationCodeEmail(requestJson.get("emailAddress").getAsString());
				session.getServletContext().setAttribute("vCode", code);
				session.getServletContext().setAttribute("username", requestJson.get("emailAddress").getAsString());
				return ResponseEntity.ok("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return ResponseEntity.ok(e.getMessage());
		}
		return ResponseEntity.ok("Invalid Email");

	}
}
