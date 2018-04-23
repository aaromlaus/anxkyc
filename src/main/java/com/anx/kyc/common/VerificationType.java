package com.anx.kyc.common;

public enum VerificationType {
	
	EMAIL_VERIFICATION("alert alert-success"), 
	PHONE_VERIFICATION("alert alert-info"), 
	SELFIE_VERIFICATION("alert alert-warning"), 
	IDENTIFICATION_VERIFICATION("alert alert-danger"),
	ADDRESS_VERIFICATION("alert alert-danger");

	private final String value;

	private VerificationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
 
}
