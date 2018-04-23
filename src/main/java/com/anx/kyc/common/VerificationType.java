package com.anx.kyc.common;

public enum VerificationType {
	
	EMAIL_VERIFICATION(UserLevelType.LEVEL_2), 
	PHONE_VERIFICATION(UserLevelType.LEVEL_2), 
	SELFIE_VERIFICATION(UserLevelType.LEVEL_2), 
	IDENTIFICATION_VERIFICATION(UserLevelType.LEVEL_2),
	ADDRESS_VERIFICATION(UserLevelType.LEVEL_3);

	private final String value;

	private VerificationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
 
}
