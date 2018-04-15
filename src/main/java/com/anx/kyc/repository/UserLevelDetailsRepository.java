package com.anx.kyc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.UserLevelDetails;

public interface UserLevelDetailsRepository extends JpaRepository<UserLevelDetails, Integer> {
	@Query("SELECT c FROM UserLevelDetails c WHERE c.anxUser = :anxUser ORDER BY level ")
	List<UserLevelDetails> findUserLevelDetailsById(@Param("anxUser") AnxUser anxUserId);
}
