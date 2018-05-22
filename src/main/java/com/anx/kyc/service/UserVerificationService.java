package com.anx.kyc.service;

import com.anx.kyc.model.AnxUser;

public interface UserVerificationService {

	public void addUserVerification(String userId);
	
	public boolean checkLevelCompletion(String level, String userId);

	public void updateVerificationStatus(AnxUser user, String verification, String status);

}
