package com.anx.kyc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

	//@Autowired
	//private LevelRepository levelRepository;

	//@Autowired
	//private UserLevelDetailsRepository levelUserRepository;
	
	@Autowired
	private PhoneCodeRepository phoneCodeRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired UserImageRepository uIrepository;
	@Override
	public int saveUser(AnxUser user) {
		
		return saveUser(user,true);
	}
	@Override
	public int saveUser(AnxUser user,boolean isEncodePassword) {
		if(isEncodePassword) {
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
	
	/*@Override
	public List<UserLevelDetails> findLevelUserById(AnxUser anxUserId) {
		return levelUserRepository.findUserLevelDetailsById(anxUserId);
	}
	@Override
	public UserLevelDetails findLevelUserByIdAndLevel(long anxUserId, long levelId) {
		return levelUserRepository.findUserLevelDetailsByIdAndLevel(anxUserId,levelId);
	}
	@Override
	public void saveNewLevelUser(AnxUser anxUser) {
		List<Level> allLevel = getAllLevel();
		List<UserLevelDetails> levelUsers = new ArrayList<>();
		for (Level level : allLevel) {
			UserLevelDetails levelUser = new UserLevelDetails(anxUser, level, level.getDescription().equalsIgnoreCase("level 1"));
			levelUsers.add(levelUser);
		}
		levelUserRepository.saveAll(levelUsers);
	}*/
	@Override
	public AnxUser findAnxUserByUsername(String userName) {
		AnxUser anx = auRepository.findAnxUserByUsername(userName);
		return anx;
	}
	@Override
	public List<PhoneCode> getAllPhoneCode(){
		return phoneCodeRepository.findAll();
	}
	@Override
	public PhoneCode findPhoneCodeById(Long id){
		return phoneCodeRepository.getOne(id);
	}
	@Override
	public void saveUserImage(UserImage image) {
		uIrepository.save(image);
	}
	
}
/*=======
package com.anx.kyc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.PhoneCode;
import com.anx.kyc.model.Role;
import com.anx.kyc.model.UserLevel;
import com.anx.kyc.repository.AnxUserRepository;
import com.anx.kyc.repository.PhoneCodeRepository;
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

	//@Autowired
	//private LevelRepository levelRepository;

	//@Autowired
	//private UserLevelDetailsRepository levelUserRepository;
	@Autowired
	private PhoneCodeRepository phoneCodeRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public int saveUser(AnxUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
	public List<UserLevelDetails> findLevelUserById(AnxUser anxUserId) {
		return levelUserRepository.findUserLevelDetailsById(anxUserId);
	}
	@Override
	public UserLevelDetails findLevelUserByIdAndLevel(long anxUserId, long levelId) {
		return levelUserRepository.findUserLevelDetailsByIdAndLevel(anxUserId,levelId);
	}
	@Override
	public void saveNewLevelUser(AnxUser anxUser) {
		List<Level> allLevel = getAllLevel();
		List<UserLevelDetails> levelUsers = new ArrayList<>();
		for (Level level : allLevel) {
			UserLevelDetails levelUser = new UserLevelDetails(anxUser, level, level.getDescription().equalsIgnoreCase("level 1"));
			levelUsers.add(levelUser);
		}
		levelUserRepository.saveAll(levelUsers);
	}
	@Override
	public AnxUser findAnxUserByUsername(String userName) {
		return auRepository.findAnxUserByUsername(userName);
	}
	@Override
	public List<PhoneCode> getAllPhoneCode(){
		return phoneCodeRepository.findAll();
	}
	@Override
	public PhoneCode findPhoneCodeById(Long id){
		return phoneCodeRepository.getOne(id);
	}

	@Override
	public void saveNewLevelUser(AnxUser anxUser) {
		// TODO Auto-generated method stub
		
	}

}
>>>>>>> stash
*/