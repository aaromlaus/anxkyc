package com.anx.kyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anx.kyc.model.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("SELECT c FROM Role c WHERE c.roleName = :roleName ")
	Role findByRoleName(@Param("roleName") String roleName);
}
