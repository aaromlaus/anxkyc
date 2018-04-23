package com.anx.kyc.common;

public enum VerificationType {
	
	EMAIL_VERIFICATION("Level2"), 
	PHONE_VERIFICATION("Level2"), 
	SELFIE_VERIFICATION("Level2"), 
	IDENTIFICATION_VERIFICATION("Level2"),
	ADDRESS_VERIFICATION("Level3");

	private final String value;

	private VerificationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
 
}
