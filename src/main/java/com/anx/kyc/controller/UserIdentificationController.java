package com.anx.kyc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.IdentificationCard;
import com.anx.kyc.service.UserService;
import com.anx.kyc.util.AnxUtil;

@Controller
@RequestMapping(value="${url.identification}")
public class UserIdentificationController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String showIdentityVerificationPage(Map<String, Object> model) {
		return "main/identification";
	}
	
	@RequestMapping("/steps")
	public String showVerificationSteps(Map<String, Object> model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Map<String, String> countryList = AnxUtil.getCountryList();
		Map<String, String> industryList = AnxUtil.getIndustryList();
		Map<String, String> sourceList = AnxUtil.getSourceList();
		Map<String, String> idList = AnxUtil.getIdList();
		

		model.put("user",userService.findByEmailAddressOrPhoneNumber(currentPrincipalName));
		model.put("countryList", countryList);
		model.put("industryList", industryList);
		model.put("sourceList", sourceList);
		model.put("idList", idList);
		
		return "main/verificationsteps";
	}
	
	@RequestMapping(value="/saveUserDetails",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public String saveUserDetails(@ModelAttribute("user")AnxUser anxUser) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser dbUser = userService.findByEmailAddressOrPhoneNumber(currentPrincipalName);
		copyFormFieldsValue(dbUser,anxUser);
		userService.saveUser(dbUser);
		return "redirect:/profile/main";
	}
	
	
	private AnxUser copyFormFieldsValue(AnxUser to, AnxUser from) {
		
		to.setBirthDate(AnxUtil.parseFormatterdDate(from.getBirthDateStr(), "yyyy-MM-dd"));
		to.setGender(from.getGender());
		to.setEmploymentStatus(from.getEmploymentStatus());
		to.setIndustry(from.getIndustry());
		to.setPosition(from.getPosition());
		to.setEmployerName(from.getEmployerName());
		to.setFundSource(from.getFundSource());
		to.setNationality(from.getNationality());
		to.setFundSourceReason(from.getFundSourceReason());
		IdentificationCard card = from.getCard().get(0);
		card.setExpirationDate(AnxUtil.parseFormatterdDate(card.getExpDateStr(), "yyyy-MM-dd"));
		if(to.getCard().size() < 1) {
			to.getCard().add(card);
		}else {
			to.getCard().get(0).setIdType(card.getIdType());
			to.getCard().get(0).setExpirationDate(card.getExpirationDate());
			to.getCard().get(0).setIdNumber(card.getIdNumber());
			to.getCard().get(0).setFrontImg(card.getFrontImg());
			to.getCard().get(0).setBackImg(card.getBackImg());
		}
		
		return to;

	}
	
	
}
