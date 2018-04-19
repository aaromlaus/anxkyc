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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anx.kyc.common.AlertStyleMessages;
import com.anx.kyc.common.UserLevelType;
import com.anx.kyc.helper.AnxMessageHelper;
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
	private AnxMessageHelper amHelper;
	
	@Value("${file.path.upload:test}")
	private String UPLOAD_PATH;
	
	@RequestMapping(value = "/main")
	public String mainPage(Map<String, Object> model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser anxUser = userService.findByEmailAddressOrPhoneNumber(currentPrincipalName);
		List<UserLevel> userLevels = userService.getAllUserLevel();
		model.put("userLevels", userLevels);
		model.put("anxUser", anxUser);
		return "main/userdashboard";
	}
	
	@PostMapping("/upload")
	public String singleFileUpload(Map<String, Object> model,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.DANGER.getValue());
			redirectAttributes.addFlashAttribute("msgDetails",amHelper.get("user.upload.image.error"));
			return "redirect:/profile/main";
		}

		try {
			// Get the file and save it somewhere
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			AnxUser anxUser = userService.findByEmailAddressOrPhoneNumber(currentPrincipalName);
			
			byte[] bytes = file.getBytes();
			String fileName = anxUser.getFirstName()+anxUser.getMiddleName()+anxUser.getLastName()+"Id"+anxUser.getUserId();
			Path path = Paths.get(UPLOAD_PATH + fileName);
			Files.write(path, bytes);
			
			anxUser.setUserLevel(userService.getUserLevel(UserLevelType.LEVEL_2_PENDING));
			userService.saveUser(anxUser,false);
			UserImage image = new UserImage(anxUser, path.toString());
			userService.saveUserImage(image);
			redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.SUCCESS.getValue());
			redirectAttributes.addFlashAttribute("msgDetails", amHelper.get("user.upload.image.success"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/profile/main";
	}
	
	@RequestMapping(value = "/myaccount")
	public String myAccount(Map<String, Object> model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser anxUser = userService.findByEmailAddressOrPhoneNumber(currentPrincipalName);
		model.put("anxUserForm", anxUser);
		return "main/myaccount";
	}
	@RequestMapping(value = "/myaccount/changePassword")
	public String changePassword(Map<String, Object> model,@ModelAttribute("anxUserForm") AnxUser anxUser,RedirectAttributes redirectAttributes) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		if (!anxUser.getPassword().equals(anxUser.getConfirmPassword())) {
			redirectAttributes.addFlashAttribute("passwordClassDisplay", "in");
			redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.DANGER.getValue());
			redirectAttributes.addFlashAttribute("msgDetails",amHelper.get("user.password.notMatch"));
			return "redirect:/profile/myaccount/";
		}
		String password = anxUser.getPassword();
		anxUser = userService.findByEmailAddressOrPhoneNumber(currentPrincipalName);
		anxUser.setPassword(password);
		userService.saveUser(anxUser);
		model.put("anxUserForm", anxUser);
		return "main/myaccount";
	}

}

