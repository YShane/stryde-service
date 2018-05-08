package com.stryde.webservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${oauth2.stryde.resource-id}")
	private String oauth2ResourceId;
	
	@Value("${app.jwt.signingkey}")
	private String signingKey;
	
	@Autowired
	private DefaultTokenServices defaultTokenServices;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors() // allowing cors with the corsConfigurationSource()
				.and().authorizeRequests()
				// swagger UI
				.antMatchers("/swagger-ui.html").permitAll().antMatchers("/swagger/**").permitAll()
				.antMatchers("/webjars/**").permitAll().antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/v2/api-docs/**").permitAll()

				// allow actuators /health ...
				.antMatchers("/actuator/**").permitAll()

				// allow public service
				.antMatchers("/auth/**").permitAll()

				.anyRequest().permitAll();
				//.anyRequest().authenticated();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.tokenServices(defaultTokenServices);
		resources.resourceId(oauth2ResourceId);
	}
}
