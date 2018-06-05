package com.anx.kyc.service;

import java.util.List;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.UserVerification;

public interface UserVerificationService {

	public void addUserVerification(String userId);
	
	public boolean checkLevelCompletion(String level, String userId);

	public void updateVerificationStatus(AnxUser user, String verification, String status, String userLevelFrom, String userLevelTo);

	public List<UserVerification> getAllUserVerification(String userId);
}
