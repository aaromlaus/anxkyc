package com.anx.kyc.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.anx.kyc")
@EnableJpaRepositories("com.anx.kyc.repository")
@EntityScan("com.anx.kyc.model")
public class KycWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KycWebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(KycWebApplication.class, args);
	}

}
