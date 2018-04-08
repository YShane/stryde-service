package com.shane.stryde.webservice.service;

import org.springframework.security.core.Authentication;

import com.shane.stryde.webservice.dto.UserDto;
import com.shane.stryde.webservice.dto.auth.RegistrationDto;

public interface AuthService {

	UserDto registerUser(RegistrationDto registrationDto);

	UserDto getActiveUserDtoByEmailAndPassword(String email, String password);
	
	UserDto getCurrentUserByAuthentication(Authentication authentication);
}
