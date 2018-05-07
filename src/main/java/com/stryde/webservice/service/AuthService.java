package com.stryde.webservice.service;

import org.springframework.security.core.Authentication;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.dto.UserMinDto;
import com.stryde.webservice.dto.auth.RegistrationDto;

public interface AuthService {

	UserDto registerUser(RegistrationDto registrationDto);

	UserDto getActiveUserDtoByEmailAndPassword(String email, String password);
	
	UserDto getCurrentUserDtoByAuthentication(Authentication authentication);
	
	UserMinDto getCurrentUserMinDtoByAuthentication(Authentication authentication);
}
