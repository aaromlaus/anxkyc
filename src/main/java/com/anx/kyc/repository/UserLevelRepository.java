package com.anx.kyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anx.kyc.model.UserLevel;


public interface UserLevelRepository extends JpaRepository<UserLevel, Integer> {

	@Query("SELECT c FROM UserLevel c WHERE c.userLevelName = :userLevelName ")
	UserLevel findByUserLevelName(@Param("userLevelName") String userLevelName);
}
	