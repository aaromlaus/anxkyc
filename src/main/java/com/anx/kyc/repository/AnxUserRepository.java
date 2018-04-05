package com.anx.kyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anx.kyc.model.AnxUser;

public interface AnxUserRepository extends JpaRepository<AnxUser, Integer> {

}
