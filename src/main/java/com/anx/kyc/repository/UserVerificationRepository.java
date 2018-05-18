package com.anx.kyc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anx.kyc.model.UserVerification;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Integer> {
	
	List<UserVerification> findByLevelAndUserId(@Param("level") String level, @Param("userId") String userId);
	
	@Transactional
    @Modifying(clearAutomatically = true)
    @Query("Update UserVerification u SET u.status = :status WHERE u.userId = :userId AND u.verification = :verification")
	void updateVerificationStatus(@Param("userId") String userId,@Param("verification") String verification,@Param("status") String status);

}
