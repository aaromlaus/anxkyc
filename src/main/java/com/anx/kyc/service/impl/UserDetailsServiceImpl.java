package com.anx.kyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.anx.kyc.model.AnxUser;
import com.anx.kyc.repository.AnxUserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AnxUserRepository auRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AnxUser user = auRepository.findByEmailAddressOrPhoneNumber(username, username);

		UserBuilder builder = null;
		if (user != null && user.isActive()) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(user.getPassword());
			builder.roles(user.getRole().getRoleName());
		} else {
	      throw new UsernameNotFoundException("User not found.");
	    }
		
		return builder.build();
	}

}
