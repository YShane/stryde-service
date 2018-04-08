package com.shane.stryde.webservice.config.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.shane.stryde.webservice.dto.UserDto;
import com.shane.stryde.webservice.exception.AppErrorCode;
import com.shane.stryde.webservice.exception.StrydeException;
import com.shane.stryde.webservice.service.AuthService;

@Component
public class DbAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	AuthService authService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();

		// check against data in db
		UserDto userDto = authService.getActiveUserDtoByEmailAndPassword(email, password);
		
		if (userDto != null) {
			// injecting authorities from database
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			
			//TODO check if this needed later on
//			userDto.getRoles().stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getUserRole().toString())));
			authorities.add(new SimpleGrantedAuthority(userDto.getUserRole().toString()));
			
			// return object that will be used in Spring security context
			// this object will contains principal name, credential and granted auth list
			// user password will not be set in the Authentication object
			// authentication name will be the user email
			return new UsernamePasswordAuthenticationToken(userDto.getEmail(), "", authorities);
		} else {
			throw new StrydeException(AppErrorCode.WRONG_CREDENTIAL);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
