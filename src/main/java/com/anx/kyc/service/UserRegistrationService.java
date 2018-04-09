package com.anx.kyc.service;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.Role;
import com.anx.kyc.model.UserLevel;

public interface UserRegistrationService {
	
	public int saveUser(AnxUser user);
	
	public Role getRole(String roleName);
	
	public UserLevel getUserLevel(String userLevelName);

}
