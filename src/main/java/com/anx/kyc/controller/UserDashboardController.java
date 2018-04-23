package com.anx.kyc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.anx.kyc.helper.AnxMessageHelper;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.PhoneCode;
import com.anx.kyc.model.UserLevel;
import com.anx.kyc.service.FileUploadService;
import com.anx.kyc.service.UserService;
import com.anx.kyc.util.AnxUtil;

@Controller
@RequestMapping(value="${url.profile}")
public class UserDashboardController {

	@Autowired
	private UserService userService;

	@Autowired
	private AnxMessageHelper amHelper;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value = "/main")
	public String mainPage(Map<String, Object> model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser anxUser = userService.findByEmailAddressOrPhoneNumber(currentPrincipalName);
		
		session.setAttribute("fullname", AnxUtil.getAnxUserFullName(anxUser));
		List<UserLevel> userLevels = userService.getAllUserLevel();
		model.put("userLevels", userLevels);
		model.put("anxUser", anxUser);
		populate(model, session);
		return "main/userdashboard";
	}

	@PostMapping("/upload")
	public String singleFileUpload(Map<String, Object> model, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.DANGER.getValue());
			redirectAttributes.addFlashAttribute("msgDetails", amHelper.get("user.upload.image.error"));
			return "redirect:/profile/main";
		}

		fileUploadService.uploadAndSaveImage(file);
		redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.SUCCESS.getValue());
		redirectAttributes.addFlashAttribute("msgDetails", amHelper.get("user.upload.image.success"));

		return "redirect:/profile/main";
	}

	@RequestMapping(value = "/myaccount")
	public String myAccount(Map<String, Object> model, HttpSession session) {
		model.put("anxUser", userService.getLoggedInUser());
		populate(model, session);
		return "main/myaccount";
	}
	
	@RequestMapping(value = "/myaccount/changePassword")
	public String changePassword(Map<String, Object> model,@ModelAttribute("anxUser") AnxUser anxUser,RedirectAttributes redirectAttributes, 
			HttpSession session) {
		populate(model, session);
		redirectAttributes.addFlashAttribute("passwordClassDisplay", "in");
		if (!anxUser.getPassword().equals(anxUser.getConfirmPassword())) {
			
			redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.DANGER.getValue());
			redirectAttributes.addFlashAttribute("msgDetails", amHelper.get("user.password.notMatch"));
			return "redirect:/profile/myaccount/";
		}
		
		String password = anxUser.getPassword();
		anxUser = userService.getLoggedInUser();
		anxUser.setPassword(password);
		userService.saveUser(anxUser, true);
		model.put("anxUser", anxUser);
		redirectAttributes.addFlashAttribute("msgCss", AlertStyleMessages.SUCCESS.getValue());
		redirectAttributes.addFlashAttribute("msgDetails", amHelper.get("userchange.password.success"));
		return "redirect:/profile/myaccount/";
	}

	private void populate(Map<String, Object> model, HttpSession session) {
		List<PhoneCode> countryCodeList = userService.getAllPhoneCode();
		model.put("countryCodeList", countryCodeList);
		session.setAttribute("countryCodeList", countryCodeList);
	}

}
