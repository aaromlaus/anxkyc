package com.anx.kyc.common;

public enum AlertStyleMessages {

	SUCCESS("alert alert-success"), 
	INFO("alert alert-info"), 
	WARNING("alert alert-warning"), 
	DANGER("alert alert-danger");

	private final String value;

	private AlertStyleMessages(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
