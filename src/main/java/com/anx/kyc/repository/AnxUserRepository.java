package com.anx.kyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.anx.kyc.model.AnxUser;
@Service
public interface AnxUserRepository extends JpaRepository<AnxUser, Long> {

}
