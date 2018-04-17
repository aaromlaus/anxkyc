package com.anx.kyc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anx.kyc.model.AnxUser;

public interface AnxUserRepository extends JpaRepository<AnxUser, Integer> {
	
	@Query("SELECT c FROM AnxUser c WHERE c.role.roleName = :roleName")
	List<AnxUser> findByRoleName(@Param("roleName") String roleName);

	@Query("SELECT c FROM AnxUser c WHERE c.userLevel.userLevelName = :userLevelName")
	List<AnxUser> findByUserLevelName(@Param("userLevelName") String userLevelName);
	
	AnxUser findByEmailAddressOrPhoneNumber(@Param("emailAddress") String emailAddress, @Param("phoneNumber") String phoneNumber);

}
