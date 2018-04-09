package com.anx.kyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anx.kyc.model.Level;

public interface LevelRepository extends JpaRepository<Level, Integer> {
	
}
