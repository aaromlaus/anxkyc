package com.anx.kyc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.anx.kyc.model.UserVerification;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Integer> {
	
	List<UserVerification> findByUserId(@Param("userId") String userId);

}
