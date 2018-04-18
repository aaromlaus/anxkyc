package com.anx.kyc.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anx.kyc.util.AnxUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;



@RestController
@RequestMapping(value = "/api")
public class ApiController {
	@RequestMapping(value = "/sendCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendCode(@RequestBody String requestBody) {
		try {
			Gson gson = new Gson();
			JsonElement element = gson.fromJson (requestBody, JsonElement.class);
			JsonObject requestJson = element.getAsJsonObject();
			if(AnxUtil.isValidEmail(requestJson.get("emailAddress").getAsString())) {
				return ResponseEntity.ok("ok");
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.ok("Error Parsing Json");
		}
		return ResponseEntity.ok("Invalid Email");
		
	}
}
