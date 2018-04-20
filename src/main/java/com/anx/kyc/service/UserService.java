package com.anx.kyc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.PhoneCode;
import com.anx.kyc.model.Role;
import com.anx.kyc.model.UserImage;
import com.anx.kyc.model.UserLevel;

public interface UserService {

	public int saveUser(AnxUser user);

	public int saveUser(AnxUser user, boolean isEncodePassword);

	public Role getRole(String roleName);

	public UserLevel getUserLevel(String userLevelName);

	public List<UserLevel> getAllUserLevel();

	public AnxUser findByEmailAddressOrPhoneNumber(String userName);

	public List<AnxUser> getAllUsers();

	public List<AnxUser> getUsersByRoleName(String roleName);

	public List<AnxUser> getUsersByUserLevelName(String userLevelName);

	public AnxUser getUserById(int userId);

	public List<PhoneCode> getAllPhoneCode();

	public PhoneCode findPhoneCodeById(Long id);

	public void saveUserImage(UserImage image);

	public AnxUser findByVerificationCode(String verificationCode);

	public AnxUser verifyAndActivateUser(String verificationCode);

	public String saveUserDetails(AnxUser anxUser);

	public void prepareAndSendUserRegistrationEmail(AnxUser anxUser, String verificationCode,
			HttpServletRequest request);

	public AnxUser findByEmailAddress(String emailAddress);

	public AnxUser findByPhoneNumber(String phoneNumber);

	public AnxUser getLoggedInUser();

	public String sendEmailChangeCode(String requestBody, HttpSession session);

	public String changeEmailCode(String requestBody, HttpSession session);

	public String changePhoneNumber(String requestBody, HttpSession session);

	public void updateAnxUserLevel(int userId, String status);
}
