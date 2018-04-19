package com.anx.kyc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.EmailService;
import com.anx.kyc.service.UserService;
import com.anx.kyc.util.AnxUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
	@Autowired
	UserService userService;
	@Autowired
	private EmailService emailService;

	@Value("${file.path.upload:test}")
	private String UPLOAD_PATH;

	@RequestMapping(value = "/sendEmailChangeCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendEmailChangeCode(@RequestBody String requestBody, HttpSession session) {
		try {
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(requestBody, JsonElement.class);
			JsonObject requestJson = element.getAsJsonObject();

			if (!requestJson.get("emailAddress").isJsonNull()
					&& AnxUtil.isValidEmail(requestJson.get("emailAddress").getAsString())) {
				AnxUser duplicateUser = userService.findByEmailAddress(requestJson.get("emailAddress").getAsString());
				if (null != duplicateUser) {
					return ResponseEntity.ok("Email Already Exists");
				}
				int code = emailService.sendChangeEmailVerificationCode(requestJson.get("emailAddress").getAsString());
				session.getServletContext().setAttribute("myAccountCode", code);
				session.getServletContext().setAttribute("myAccountEmail",
						requestJson.get("emailAddress").getAsString());
				return ResponseEntity.ok("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e.getMessage());
		}
		return ResponseEntity.ok("Invalid Email");

	}

	@RequestMapping(value = "/changeEmailCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changeEmailCode(@RequestBody String requestBody, HttpSession session) {
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(requestBody, JsonElement.class);
		JsonObject requestJson = element.getAsJsonObject();
		String verificationCode = String.valueOf(session.getServletContext().getAttribute("myAccountCode"));
		if (!requestJson.get("verificationCode").isJsonNull()
				&& !requestJson.get("verificationCode").getAsString().equals("")
				&& verificationCode.equals(requestJson.get("verificationCode").getAsString())) {
			session.getServletContext().removeAttribute("verificationCode");
			String userNamePhone = null;
			if (!requestJson.get("currentPhone").isJsonNull()
					&& !requestJson.get("currentPhone").getAsString().equals("")) {
				userNamePhone = requestJson.get("currentPhone").getAsString();
			}
			if (!requestJson.get("currentEmail").isJsonNull()
					&& !requestJson.get("currentEmail").getAsString().equals("")) {
				userNamePhone = requestJson.get("currentEmail").getAsString();
			}
			AnxUser user = userService.findByEmailAddressOrPhoneNumber(userNamePhone);
			if (null != user) {
				session.getServletContext().removeAttribute("myAccountCode");
				session.getServletContext().removeAttribute("myAccountEmail");
				user.setEmailAddress(String.valueOf(session.getServletContext().getAttribute("myAccountEmail")));
				userService.saveUser(user, false);
				return ResponseEntity.ok("ok");
			}
			ResponseEntity.ok("No user found");

		}

		return ResponseEntity.ok("Add Phone Number");
	}

	@RequestMapping(value = "/getUploadedId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String getUploadedId(@RequestBody String requestBody, HttpSession session) {
		try {
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(requestBody, JsonElement.class);
			JsonObject requestJson = element.getAsJsonObject();
			if (!requestJson.get("id").isJsonNull()) {
				AnxUser user = userService.getUserById(requestJson.get("id").getAsInt());
				String fileName = user.getFirstName() + user.getMiddleName() + user.getLastName() + "Id"
						+ user.getUserId();
				String imagePath = UPLOAD_PATH + fileName;

				String img64 = AnxUtil.encodeBase64(imagePath);
				return "data:image/png;base64," + img64;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	@RequestMapping(value = "/changePhoneNumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changePhoneNumber(@RequestBody String requestBody, HttpSession session) {

		Gson gson = new Gson();
		JsonElement element = gson.fromJson(requestBody, JsonElement.class);
		JsonObject requestJson = element.getAsJsonObject();
		if (!requestJson.get("phoneNumber").isJsonNull() && !requestJson.get("phoneNumber").getAsString().equals("")) {
			if(null != userService.findByPhoneNumber(requestJson.get("phoneNumber").getAsString())) {
				return ResponseEntity.ok("Phone Number Already Exists");
			}
			String userNamePhone = null;
			if (!requestJson.get("currentPhone").isJsonNull()
					&& !requestJson.get("currentPhone").getAsString().equals("")) {
				userNamePhone = requestJson.get("currentPhone").getAsString();
			}
			if (!requestJson.get("currentEmail").isJsonNull()
					&& !requestJson.get("currentEmail").getAsString().equals("")) {
				userNamePhone = requestJson.get("currentEmail").getAsString();
			}
			AnxUser user = userService.findByEmailAddressOrPhoneNumber(userNamePhone);
			if (null != user) {				
				user.setPhoneNumber(requestJson.get("phoneNumber").getAsString());
				user.setPhoneCode(userService.findPhoneCodeById(Long.valueOf(requestJson.get("phoneCodeId").getAsString())));
				userService.saveUser(user, false);
				return ResponseEntity.ok("ok");
			}
			ResponseEntity.ok("No user found");

		}

		return ResponseEntity.ok("Add phone number");

	}

}
