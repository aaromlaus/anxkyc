package com.anx.kyc.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.anx.kyc.common.UserLevelType;
import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.UserImage;
import com.anx.kyc.service.FileUploadService;
import com.anx.kyc.service.UserService;
import com.anx.kyc.service.UserVerificationService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserVerificationService uvService;
	
	@Value("${file.path.upload:test}")
	private String UPLOAD_PATH;
	
	@Override
	public void uploadAndSaveImage(MultipartFile file,AnxUser anxUser) {
		
		byte[] bytes;
		try {
			File directory = new File(UPLOAD_PATH);
			if(!directory.exists()) {
				directory.mkdirs();
			}
			bytes = file.getBytes();
			Long systemMills = System.currentTimeMillis();
			String fileName = anxUser.getFirstName()+anxUser.getMiddleName()+anxUser.getLastName()+"Id"+anxUser.getUserId()+"_"+systemMills;
			Path path = Paths.get(UPLOAD_PATH + fileName);
			Files.write(path, bytes);
			
			if(uvService.checkLevelCompletion(UserLevelType.LEVEL_2, anxUser.getUserId())) {
				anxUser.setUserLevel(userService.getUserLevel(UserLevelType.LEVEL_2_PENDING));
			}
			
			if(uvService.checkLevelCompletion(UserLevelType.LEVEL_3, anxUser.getUserId())) {
				anxUser.setUserLevel(userService.getUserLevel(UserLevelType.LEVEL_3_PENDING));
			}
			
			userService.saveUser(anxUser,false);
			UserImage image = new UserImage(anxUser, path.toString());
			userService.saveUserImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
