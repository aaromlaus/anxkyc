package com.anx.kyc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anx.kyc.common.AlertStyleMessages;
import com.anx.kyc.common.AnxMessageService;
import com.anx.kyc.common.UserLevelType;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.UserImage;
import com.anx.kyc.model.UserLevel;
import com.anx.kyc.service.UserService;

@Controller
@RequestMapping("/profile")
public class UserDashboardController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AnxMessageService amService;
	
	@Value("${file.path.upload:test}")
	private String UPLOAD_PATH;
	
	@RequestMapping(value = "/main")
	public String mainPage(Map<String, Object> model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser anxUser = userService.findAnxUserByUsername(currentPrincipalName);
		//anxUser.setUserLevelDetails(userService.findLevelUserById(anxUser));
		List<UserLevel> userLevels = userService.getAllUserLevel();
		model.put("userLevels", userLevels);
		model.put("anxUser", anxUser);
		return "main/user";
	}
	
	@PostMapping("/upload")
	public String singleFileUpload(Map<String, Object> model,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.DANGER.getValue());
			redirectAttributes.addFlashAttribute("msgDetails",amService.get("user.upload.image.error"));
			return "redirect:/profile/main";
		}

		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			String fileName = String.valueOf(System.currentTimeMillis());
			Path path = Paths.get(UPLOAD_PATH + fileName);
			Files.write(path, bytes);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			AnxUser anxUser = userService.findAnxUserByUsername(currentPrincipalName);
			anxUser.setUserLevel(userService.getUserLevel(UserLevelType.LEVEL_2_PENDING));
			userService.saveUser(anxUser,false);
			UserImage image = new UserImage(anxUser, path.toString());
			userService.saveUserImage(image);
			redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.SUCCESS.getValue());
			redirectAttributes.addFlashAttribute("msgDetails", amService.get("user.upload.image.success"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/profile/main";
	}

}

