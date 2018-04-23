package com.anx.kyc.service.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anx.kyc.common.VerificationStatusType;
import com.anx.kyc.common.VerificationType;
import com.anx.kyc.model.UserVerification;
import com.anx.kyc.repository.UserVerificationRepository;
import com.anx.kyc.service.UserVerificationService;

@Service
public class UserVerificationServiceImpl implements UserVerificationService {

	@Autowired
	private UserVerificationRepository uvReposity;
	
	@Override
	public void addUserVerification(String userId) {
		List<VerificationType> verList = new ArrayList<VerificationType>(EnumSet.allOf(VerificationType.class));
		for(VerificationType verType : verList) {
			UserVerification ver = new UserVerification();
			ver.setUserId(userId);
			ver.setVerification(verType.toString());
			ver.setStatus(VerificationStatusType.NOT_STARTED);
			ver.setLevel(verType.getValue());
			uvReposity.saveAndFlush(ver);
		}
	}
	
	public boolean checkLevelCompletion(String level, String userId) {
		for(UserVerification uv : uvReposity.findByLevelAndUserId(level, userId)) {
			if(!uv.getStatus().equalsIgnoreCase(VerificationStatusType.COMPLETED)) {
				return false;
			}
		}
		return true;
	}

}
