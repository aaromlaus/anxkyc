package com.anx.kyc.springboot.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomSucessHandler customSucessHandler;
	
	private static final String USER_QUERY = "SELECT u.email_address,u.password,r.role_id FROM anx_kyc.anx_user u INNER JOIN role r USING(role_name) WHERE u.email_address=?";
	private static final String ROLE_QUERY = "SELECT u.email_address, r.role_name as role FROM anx_kyc.anx_user u INNER JOIN role r USING(role_name) WHERE u.email_address=?";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/administrator/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers("/profile/**").hasAnyAuthority("user");
		http.formLogin().failureUrl("/login?error").loginPage("/login").successHandler(customSucessHandler).permitAll()
				.and().exceptionHandling().accessDeniedPage("/accessDenied").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(USER_QUERY)
				.authoritiesByUsernameQuery(ROLE_QUERY)
				.passwordEncoder(passwordEncoder());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/api/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
