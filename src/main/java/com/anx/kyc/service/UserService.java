package com.anx.kyc.service;

import java.util.List;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.PhoneCode;
import com.anx.kyc.model.Role;
import com.anx.kyc.model.UserImage;
import com.anx.kyc.model.UserLevel;

public interface UserService {
	
	public int saveUser(AnxUser user);
	
	public int saveUser(AnxUser user,boolean isEncodePassword);
	
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
	
}
