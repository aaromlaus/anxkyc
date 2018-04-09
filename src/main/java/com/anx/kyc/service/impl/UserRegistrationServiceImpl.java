package com.anx.kyc.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.Role;
import com.anx.kyc.model.UserLevel;
import com.anx.kyc.repository.AnxUserRepository;
import com.anx.kyc.repository.RoleRepository;
import com.anx.kyc.repository.UserLevelRepository;
import com.anx.kyc.service.UserRegistrationService;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {
	
	@Autowired
	private AnxUserRepository auRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserLevelRepository ulRepository;
	
	public int saveUser(AnxUser user) {
		AnxUser anx = auRepository.save(user);
		auRepository.flush();
		return anx.getUserId();
	}
	
	public Role getRole(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}
	
	public UserLevel getUserLevel(String userLevelName) {
		return ulRepository.findByuserLevelName(userLevelName);
	}

}
