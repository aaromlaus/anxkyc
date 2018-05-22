package com.anx.kyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anx.kyc.model.Address;
import com.anx.kyc.model.AddressVerificationDoc;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.service.UserService;
import com.anx.kyc.util.AnxUtil;

@Controller
@RequestMapping(value="/address/verification")
public class AddressVerificationController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String showIdentityVerificationPage(Map<String, Object> model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser anxUser = userService.findByEmailAddressOrPhoneNumber(currentPrincipalName);

		Map<String, String> countryList = AnxUtil.getCountryList();
		Map<String, String> docTypeList = AnxUtil.getDocTypeList();

		model.put("user", anxUser);
		model.put("countryList", countryList);
		model.put("docTypeList", docTypeList);
		
		return "verification/addressverification";
	}

	@RequestMapping(value = "/submitAddressVerification", method = RequestMethod.POST)
	public String submitAddressVerification(@ModelAttribute("user")AnxUser anxUser) {
		
		anxUser.getAddressVerificationDoc().setExpirationDate(AnxUtil.parseFormatterdDate(anxUser.getAddressVerificationDoc().getExpirationDateStr(), "yyyy-MM-dd"));
		if(anxUser.isCurrentAddressSame()) {
			copyAddress(anxUser.getPermanentAddress(),anxUser.getCurrentAddress());
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		AnxUser dbUser = userService.findByEmailAddressOrPhoneNumber(currentPrincipalName);
		copyAddressDetails(dbUser,anxUser);
		userService.saveUser(dbUser);
		System.out.println(anxUser);
		
		return "redirect:/address/verification/";
	}
	
	private void copyAddressDetails(AnxUser to, AnxUser from) {
		if(to.getCurrentAddress() == null) {
			to.setCurrentAddress(from.getCurrentAddress());
		}else {
			copyAddress(to.getCurrentAddress(), from.getCurrentAddress());
		}
		if(to.getPermanentAddress() == null) {
			to.setPermanentAddress(from.getPermanentAddress());
		}else {
			copyAddress(to.getPermanentAddress(), from.getPermanentAddress());
		}
		if(to.getAddressVerificationDoc() == null) {
			to.setAddressVerificationDoc(from.getAddressVerificationDoc());
		}else {
			copyAddressDoc(to.getAddressVerificationDoc(),from.getAddressVerificationDoc());
		}
		to.setCurrentAddressSame(from.isCurrentAddressSame());
	}
	
	private void copyAddress(Address to, Address from) {
		to.setUnitNo(from.getUnitNo());
		to.setCity(from.getCity());
		to.setState(from.getState());
		to.setPostalCode(from.getPostalCode());
		to.setCountry(from.getCountry());
	}
	
	private void copyAddressDoc(AddressVerificationDoc to, AddressVerificationDoc from) {
		to.setType(from.getType());
		to.setFileName(from.getFileName());
		to.setFile(from.getFile());
		to.setExpirationDate(from.getExpirationDate());
		to.setDocumentShowsName(from.isDocumentShowsName());
	}
	

	
}
