package com.stryde.webservice.config;

import com.stryde.webservice.config.provider.DbAuthenticationProvider;
import com.stryde.webservice.service.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.stryde.webservice.config.provider.DbAuthenticationProvider;
import com.stryde.webservice.service.SecurityService;
import com.stryde.webservice.service.SecurityServiceImpl;;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DbAuthenticationProvider dbAuthenticationProvider;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.cors() // allowing cors with the corsConfigurationSource()
			.and()
			.authorizeRequests()
				// swagger UI
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/v2/api-docs/**").permitAll()
				
				// allow actuators /health ...
				.antMatchers("/actuator/**").permitAll()
				
				// allow public service
				.antMatchers("/auth/**").permitAll()
				
				.anyRequest().authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(dbAuthenticationProvider);
	}
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
	  return authenticationManager();
	}
	
	@Bean
	public SecurityService securityService() {
		return new SecurityServiceImpl();
	}

}