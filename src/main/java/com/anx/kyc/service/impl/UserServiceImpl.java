package com.anx.kyc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anx.kyc.common.RoleType;
import com.anx.kyc.common.UserLevelType;
import com.anx.kyc.helper.EmailHelper;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.PhoneCode;
import com.anx.kyc.model.Role;
import com.anx.kyc.model.UserImage;
import com.anx.kyc.model.UserLevel;
import com.anx.kyc.repository.AnxUserRepository;
import com.anx.kyc.repository.PhoneCodeRepository;
import com.anx.kyc.repository.RoleRepository;
import com.anx.kyc.repository.UserImageRepository;
import com.anx.kyc.repository.UserLevelRepository;
import com.anx.kyc.service.EmailService;
import com.anx.kyc.service.UserService;
import com.anx.kyc.service.UserVerificationService;
import com.anx.kyc.util.AnxUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private AnxUserRepository auRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserLevelRepository ulRepository;

	@Autowired
	private PhoneCodeRepository phoneCodeRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserImageRepository uIrepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private EmailHelper emailHelper;

	@Autowired
	private UserVerificationService uvService;

	private Gson gson = new Gson();

	@Override
	public int saveUser(AnxUser user, boolean isEncodePassword) {
		if (isEncodePassword) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		AnxUser anx = auRepository.save(user);
		auRepository.flush();
		return anx.getId();
	}

	@Override
	public int saveUser(AnxUser user) {
		return saveUser(user, false);
	}

	@Override
	public AnxUser getUserById(String userId) {
		return auRepository.findByUserId(userId);
	}

	@Override
	public Role getRole(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	@Override
	public UserLevel getUserLevel(String userLevelName) {
		return ulRepository.findByUserLevelName(userLevelName);
	}

	@Override
	public List<AnxUser> getUsersByRoleName(String roleName) {
		return auRepository.findByRoleName(roleName);
	}

	@Override
	public List<AnxUser> getUsersByUserLevelName(String userLevelName) {
		return auRepository.findByUserLevelName(userLevelName);
	}

	@Override
	public List<AnxUser> getAllUsers() {
		return auRepository.findAll();
	}

	@Override
	public List<UserLevel> getAllUserLevel() {
		return ulRepository.findAll();
	}

	@Override
	public AnxUser findByEmailAddressOrPhoneNumber(String userName) {
		return auRepository.findByEmailAddressOrPhoneNumber(userName, userName);
	}

	@Override
	public List<PhoneCode> getAllPhoneCode() {
		return phoneCodeRepository.findAll();
	}

	@Override
	public PhoneCode findPhoneCodeById(Long id) {
		return phoneCodeRepository.getOne(id);
	}

	@Override
	public void saveUserImage(UserImage image) {
		uIrepository.save(image);
	}

	@Override
	public AnxUser findByVerificationCode(String verificationCode) {
		return auRepository.findByVerificationCode(verificationCode);
	}

	@Override
	public AnxUser verifyAndActivateUser(String verificationCode) {
		if (verificationCode != null && !verificationCode.isEmpty()) {
			AnxUser user = findByVerificationCode(verificationCode);
			if (user != null) {
				user.setActive(true);
				user.setVerificationCode("");
				saveUser(user, false);
				return user;
			}
		}

		return null;
	}

	@Override
	public String generateandSetVerificationCode(AnxUser anxUser) {
		String verificationCode = UUID.randomUUID().toString();
		anxUser.setVerificationCode(verificationCode);

		return verificationCode;
	}

	@Override
	public String saveUserDetails(AnxUser anxUser) {
		anxUser.setRole(getRole(RoleType.USER));
		anxUser.setUserLevel(getUserLevel(UserLevelType.LEVEL_1));
		if (null != anxUser && null != anxUser.getPhoneCode() && null != anxUser.getPhoneCode().getPhoneCodeId()) {
			anxUser.setPhoneCode(findPhoneCodeById(anxUser.getPhoneCode().getPhoneCodeId()));
		}

		int id = saveUser(anxUser, true);
		String userId = AnxUtil.generateId("ANX", id);

		anxUser.setUserId(userId);
		saveUser(anxUser);

		return userId;
	}

	@Override
	public void prepareAndSendUserRegistrationEmail(AnxUser anxUser, String verificationCode,
			HttpServletRequest request) {
		// Prepare email verification link
		String emailLink = AnxUtil.getRequestPath(request) + "/signup/verify?details=" + verificationCode;
		Map<String, String> emailRegistrationParam = new HashMap<String, String>();
		emailRegistrationParam.put("verificationCode", emailLink);

		// Send email
		String emailContent = emailHelper.buildUserSignupVerificationEmailContent(emailRegistrationParam);
		List<String> emailToList = new ArrayList<String>();
		emailToList.add(anxUser.getEmailAddress());
		emailService.sendEmail(emailToList, "Complete your Signup", emailContent);
	}

	@Override
	public AnxUser findByEmailAddress(String emailAddress) {
		return auRepository.findByEmailAddress(emailAddress);
	}

	@Override
	public AnxUser findByPhoneNumber(String phoneNumber) {
		return auRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public AnxUser getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return findByEmailAddressOrPhoneNumber(currentPrincipalName);
	}

	@Override
	public String sendEmailChangeCode(String requestBody, HttpSession session) {
		JsonElement element = gson.fromJson(requestBody, JsonElement.class);
		JsonObject requestJson = element.getAsJsonObject();

		if (!requestJson.get("emailAddress").isJsonNull()
				&& AnxUtil.isValidEmail(requestJson.get("emailAddress").getAsString())) {
			AnxUser duplicateUser = findByEmailAddress(requestJson.get("emailAddress").getAsString());
			if (null != duplicateUser) {
				return "Email Already Exists";
			}
			int code = emailService.sendChangeEmailVerificationCode(requestJson.get("emailAddress").getAsString());
			session.getServletContext().setAttribute("myAccountCode", code);
			session.getServletContext().setAttribute("myAccountEmail", requestJson.get("emailAddress").getAsString());
			return "ok";
		}
		return "Error";
	}

	@Override
	public String changeEmailCode(String requestBody, HttpSession session) {
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
			AnxUser user = findByEmailAddressOrPhoneNumber(userNamePhone);
			if (null != user) {

				user.setEmailAddress(String.valueOf(session.getServletContext().getAttribute("myAccountEmail")));
				checkAndUpdateLevel2Completion(user);

				saveUser(user, false);
				session.getServletContext().removeAttribute("myAccountCode");
				session.getServletContext().removeAttribute("myAccountEmail");
				if (null != requestJson.get("currentPage") && !requestJson.get("currentPage").isJsonNull()
						&& !requestJson.get("currentPage").getAsString().equals("")) {
					return requestJson.get("currentPage").getAsString();
				}
				return "ok";
			}
			return "No user found";

		}
		return "Incorrect code";
	}

	@Override
	public String changePhoneNumber(String requestBody, HttpSession session) {
		JsonElement element = gson.fromJson(requestBody, JsonElement.class);
		JsonObject requestJson = element.getAsJsonObject();
		if (!requestJson.get("phoneNumber").isJsonNull() && !requestJson.get("phoneNumber").getAsString().equals("")) {
			if (null != findByPhoneNumber(requestJson.get("phoneNumber").getAsString())) {
				return "Phone Number Already Exists";
			}
			String userNamePhone = null;
			if (null != requestJson.get("currentPhone") && !requestJson.get("currentPhone").isJsonNull()
					&& !requestJson.get("currentPhone").getAsString().equals("")) {
				userNamePhone = requestJson.get("currentPhone").getAsString();
			}
			if (null != requestJson.get("currentEmail") && !requestJson.get("currentEmail").isJsonNull()
					&& !requestJson.get("currentEmail").getAsString().equals("")) {
				userNamePhone = requestJson.get("currentEmail").getAsString();
			}
			AnxUser user = findByEmailAddressOrPhoneNumber(userNamePhone);
			if (null != user) {
				user.setPhoneNumber(requestJson.get("phoneNumber").getAsString());
				user.setPhoneCode(findPhoneCodeById(Long.valueOf(requestJson.get("phoneCodeId").getAsString())));
				checkAndUpdateLevel2Completion(user);
				saveUser(user, false);
				if (null != requestJson.get("currentPage") && !requestJson.get("currentPage").isJsonNull()
						&& !requestJson.get("currentPage").getAsString().equals("")) {
					return requestJson.get("currentPage").getAsString();
				}
				return "ok";
			}
			ResponseEntity.ok("No user found");

		}
		return "Add phone number";
	}

	@Override
	public void updateAnxUserLevel(String userId, String status) {
		AnxUser user = getUserById(userId);
		if (status.equalsIgnoreCase("approve")) {
			user.setUserLevel(getUserLevel(UserLevelType.LEVEL_2));
		} else if (status.equalsIgnoreCase("reject")) {
			user.setUserLevel(getUserLevel(UserLevelType.LEVEL_1));
		}
		saveUser(user, false);
	}

	public AnxUser getAnxUserById(int id) {
		return auRepository.findById(id);
	}

	private void checkAndUpdateLevel2Completion(AnxUser user) {
		if (uvService.checkLevelCompletion(UserLevelType.LEVEL_2, user.getUserId())) {
			user.setUserLevel(getUserLevel(UserLevelType.LEVEL_2_PENDING));
		}
	}

}
