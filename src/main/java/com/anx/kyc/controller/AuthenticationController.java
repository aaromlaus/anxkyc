package com.anx.kyc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anx.kyc.common.AnxSession;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.AuthenticationForm;
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
		model.put("loginForm", new AuthenticationForm());
		return "login";
	}

	@RequestMapping(value = "/doLogin")
	public String doLogin(@ModelAttribute("loginForm") AuthenticationForm login) {
		return "welcome";
	}


	@RequestMapping("/forgotPassword")
	public String forgotPassword(Map<String, Object> model, HttpSession session) {
		model.put("command", new AuthenticationForm());
		return "auth/forgotpassword";
	}
	
	@RequestMapping("/sendCode")
	public String sendCode(@RequestParam("username") String username,Map<String, Object> model, HttpSession session, RedirectAttributes redirectAttributes) {
		model.put("command", new AuthenticationForm());
		if(username.isEmpty()) {
			redirectAttributes.addAttribute("errorMsg", "Please enter email address or password.");
			return "redirect:/forgotPassword";
		}
		if(AnxUtil.isValidEmail(username)) {
			if(userService.findByEmailAddressOrPhoneNumber(username) != null) {
				int code = emailService.sendVerificationCodeEmail(username);
				session.getServletContext().setAttribute("vCode", code);
				session.getServletContext().setAttribute("username", username);
				model.put("command", new AuthenticationForm());
				return "redirect:/verifyCode";
			}else {
				redirectAttributes.addAttribute("errorMsg", "User account not found!");
				return "redirect:/forgotPassword";
			}
			
		}else {
			redirectAttributes.addAttribute("errorMsg", "Value is not a valid email address!");
			return "redirect:/forgotPassword";
		}
	}
	
	@RequestMapping("/verifyCode")
	public String verifyCode(Map<String, Object> model, HttpSession session) {
		model.put("command", new AuthenticationForm());
		return "auth/verifycode";
	}
	
	@RequestMapping("/doVerify")
	public String verifyCode(Map<String, Object> model, @RequestParam("code") String code, HttpSession session, RedirectAttributes redirectAttributes) {
		if(code.isEmpty()) {
			redirectAttributes.addAttribute("errorMsg", "Please enter validation code.");
			return "redirect:/verifyCode";
		}
		String vCode = String.valueOf(session.getServletContext().getAttribute("vCode"));
		model.put("command", new AuthenticationForm());
		if(vCode.equals(code)) {
			return "redirect:/resetPassword";
		}else {
			redirectAttributes.addAttribute("errorMsg", "Incorrect Verification code!");
			return "redirect:/verifyCode";
		}
		
	}
	
	
	@RequestMapping("/resetPassword")
	public String resetPasword(Map<String, Object> model, HttpSession session) {
		model.put("command", new AuthenticationForm());
		return "auth/resetpassword";
	}
	
	@RequestMapping("/doResetPassword")
	public String resetPasword(Map<String, Object> model, @ModelAttribute("command") AuthenticationForm form, HttpSession session, RedirectAttributes redirectAttributes) {
		if(form.getPassword().isEmpty() || form.getConfirmPassword().isEmpty() ) {
			redirectAttributes.addAttribute("errorMsg", "Please enter a value for both fields.");
			return "redirect:/resetPassword";
		}
		if(form.getPassword().equals(form.getConfirmPassword())) {
			String username = String.valueOf(session.getServletContext().getAttribute("username"));
			AnxUser user = userService.findByEmailAddressOrPhoneNumber(username);
			user.setPassword(form.getConfirmPassword());
			userService.saveUser(user);
			return "redirect:/resetSuccess";
		}else {
			redirectAttributes.addAttribute("errorMsg", "Password did not match!");
			return "redirect:/resetPassword";
		}
	}	
	
	@RequestMapping("/resetSuccess")
	public String resetSuccess(Map<String, Object> model, HttpSession session) {
		model.put("command", new AuthenticationForm());
		return "auth/resetsuccess";
	}

}
