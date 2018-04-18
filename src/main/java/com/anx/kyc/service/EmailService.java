package com.anx.kyc.service;


public interface EmailService {
	void sendVerificationCodeEmail(String emailAddress);
}
