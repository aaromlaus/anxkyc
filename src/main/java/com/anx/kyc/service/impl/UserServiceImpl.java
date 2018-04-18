package com.anx.kyc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.anx.kyc.util.AnxUtil;

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

	@Override
	public int saveUser(AnxUser user) {

		return saveUser(user, true);
	}

	@Override
	public int saveUser(AnxUser user, boolean isEncodePassword) {
		if (isEncodePassword) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		AnxUser anx = auRepository.save(user);
		auRepository.flush();
		return anx.getUserId();
	}

	@Override
	public AnxUser getUserById(int userId) {
		return auRepository.getOne(userId);
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
	public void verifyAndActivateUser(String verificationCode) {
		if(verificationCode != null && !verificationCode.isEmpty()) {
			AnxUser user = findByVerificationCode(verificationCode);
			if(user != null) {
				user.setActive(true);
				user.setVerificationCode("");
				saveUser(user, false);
			}
		}
	}
	
	@Override
	public String saveUserDetails(AnxUser anxUser) {
		anxUser.setRole(getRole(RoleType.USER));
		anxUser.setUserLevel(getUserLevel(UserLevelType.LEVEL_1));
		if (null != anxUser && null != anxUser.getPhoneCode() && null != anxUser.getPhoneCode().getPhoneCodeId()) {
			anxUser.setPhoneCode(findPhoneCodeById(anxUser.getPhoneCode().getPhoneCodeId()));
		}
		
		UUID uuid = UUID.randomUUID();
		String verificationCode = uuid.toString();
		anxUser.setVerificationCode(verificationCode);
		saveUser(anxUser);
		return verificationCode;
	}
	
	@Override
	public void prepareAndSendUserRegistrationEmail(AnxUser anxUser, String verificationCode, HttpServletRequest request) {
		// Prepare email verification link
		String emailLink = AnxUtil.getRequestPath(request) + "/verify?details=" + verificationCode;
		Map<String, String> emailRegistrationParam = new HashMap<String, String>();
		emailRegistrationParam.put("verificationCode", emailLink);
		
		// Send email
		String emailContent = emailHelper.buildUserSignupVerificationEmailContent(emailRegistrationParam);
		List<String> emailToList = new ArrayList<String>();
		emailToList.add(anxUser.getEmailAddress());
		emailService.sendEmail(emailToList, "Complete your Signup", emailContent);
	}
}
