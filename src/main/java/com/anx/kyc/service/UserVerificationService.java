package com.anx.kyc.service;

public interface UserVerificationService {

	public void addUserVerification(String userId);
	
	public boolean checkLevelCompletion(String level, String userId);

}
