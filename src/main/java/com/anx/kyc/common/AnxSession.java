package com.anx.kyc.common;

import org.springframework.stereotype.Component;

@Component
public class AnxSession {

	private String homeUrl;

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}
	
}
