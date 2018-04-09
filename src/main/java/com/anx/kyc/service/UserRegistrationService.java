package com.anx.kyc.service;

import java.util.List;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.Level;
import com.anx.kyc.model.LevelUser;
import com.anx.kyc.model.Role;
import com.anx.kyc.model.UserLevel;

public interface UserRegistrationService {
	
	public int saveUser(AnxUser user);
	
	public Role getRole(String roleName);
	
	public UserLevel getUserLevel(String userLevelName);
	
	public List<Level> getAllLevel();
	
	public List<LevelUser> findLevelUserById(AnxUser anxUserId);
	
	public void saveNewLevelUser(AnxUser anxUser);
	
	public AnxUser findAnxUserByUsername(String userName);
	
}
