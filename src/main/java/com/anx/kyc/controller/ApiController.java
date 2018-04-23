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

	@Value("${file.path.upload:test}")
	private String UPLOAD_PATH;

	@RequestMapping(value = "/sendEmailChangeCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendEmailChangeCode(@RequestBody String requestBody, HttpSession session) {
		try {

			return ResponseEntity.ok(userService.sendEmailChangeCode(requestBody, session));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e.getMessage());
		}

	}

	@RequestMapping(value = "/changeEmailCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changeEmailCode(@RequestBody String requestBody, HttpSession session) {

		return ResponseEntity.ok(userService.changeEmailCode(requestBody, session));
	}

	@RequestMapping(value = "/getUploadedId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String getUploadedId(@RequestBody String requestBody, HttpSession session) {
		try {
			Gson gson = new Gson();
			JsonElement element = gson.fromJson(requestBody, JsonElement.class);
			JsonObject requestJson = element.getAsJsonObject();
			if (!requestJson.get("id").isJsonNull()) {
				AnxUser user = userService.getUserById(requestJson.get("id").getAsString());
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

		return ResponseEntity.ok(userService.changePhoneNumber(requestBody, session));

	}


}
