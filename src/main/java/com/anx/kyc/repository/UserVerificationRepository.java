package com.anx.kyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anx.kyc.model.UserVerification;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Integer> {

}
