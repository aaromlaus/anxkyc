package com.anx.kyc.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.anx.kyc")
public class KycWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(KycWebApplication.class, args);
	}

}
