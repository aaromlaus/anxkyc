package com.anx.kyc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.model.LevelUser;

public interface LevelUserRepository extends JpaRepository<LevelUser, Integer> {
	@Query("SELECT c FROM LevelUser c WHERE c.anxUser = :anxUser ORDER BY level ")
	List<LevelUser> findLevelUserById(@Param("anxUser") AnxUser anxUserId);
}
