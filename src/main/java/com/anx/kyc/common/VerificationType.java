package com.anx.kyc.common;

public enum VerificationType {
	
	EMAIL_VERIFICATION("emailverification"), 
	PHONE_VERIFICATION("phoneverification"), 
	SELFIE_VERIFICATION("selfieverification"), 
	IDENTIFICATION_VERIFICATION("identificationverification"),
	ADDRESS_VERIFICATION("addressverification");

	private final String value;

	private VerificationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
 
}
