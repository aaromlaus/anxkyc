package com.anx.kyc.service;

import java.util.List;

public interface EmailService {
	
	int sendVerificationCodeEmail(String emailAddress);
	
	public void sendEmail(List<String> emailToList, String subject, String content);
	
	public void sendEmail(List<String> emailToList, List<String> emailCcList, String subject, String content);
	
	public void sendEmail(List<String> emailToList, List<String> emailCcList, List<String> emailBccList, String subject, String content);
	
	public int sendChangeEmailVerificationCode(String emailAddress);
	
}
