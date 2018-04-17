package com.anx.kyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anx.kyc.model.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
	
}
