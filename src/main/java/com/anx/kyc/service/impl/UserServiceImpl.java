package com.anx.kyc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.Level;
import com.anx.kyc.model.LevelUser;
import com.anx.kyc.model.Role;
import com.anx.kyc.model.UserLevel;
import com.anx.kyc.repository.AnxUserRepository;
import com.anx.kyc.repository.LevelRepository;
import com.anx.kyc.repository.LevelUserRepository;
import com.anx.kyc.repository.RoleRepository;
import com.anx.kyc.repository.UserLevelRepository;
import com.anx.kyc.service.UserService;

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
	private LevelRepository levelRepository;
	@Autowired
	private LevelUserRepository levelUserRepository;

	public int saveUser(AnxUser user) {
		AnxUser anx = auRepository.save(user);
		auRepository.flush();
		return anx.getUserId();
	}

	public Role getRole(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	public UserLevel getUserLevel(String userLevelName) {
		return ulRepository.findByUserLevelName(userLevelName);
	}

	public List<AnxUser> getUsersByRoleName(String roleName) {
		return auRepository.findByRoleName(roleName);
	}

	public List<AnxUser> getUsersByUserLevelName(String userLevelName) {
		return auRepository.findByUserLevelName(userLevelName);
	}

	public List<Level> getAllLevel() {
		return levelRepository.findAll();
	}

	public List<LevelUser> findLevelUserById(AnxUser anxUserId) {
		return levelUserRepository.findLevelUserById(anxUserId);
	}

	public void saveNewLevelUser(AnxUser anxUser) {
		List<Level> allLevel = getAllLevel();
		List<LevelUser> levelUsers = new ArrayList<>();
		for (Level level : allLevel) {
			LevelUser levelUser = new LevelUser(anxUser, level, level.getDescription().equalsIgnoreCase("level 1"));
			levelUsers.add(levelUser);
		}
		levelUserRepository.saveAll(levelUsers);
	}

	public AnxUser findAnxUserByUsername(String userName) {
		return auRepository.findAnxUserByUsername(userName);
	}

}
