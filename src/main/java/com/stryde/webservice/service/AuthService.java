package com.stryde.webservice.service;

import org.springframework.security.core.Authentication;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.dto.UserMinDto;
import com.stryde.webservice.dto.auth.RegistrationDto;

public interface AuthService {

	UserDto registerUser(RegistrationDto registrationDto);
	
	UserDto registerFacebookUser(String accessToken, String username, String email, String facebookUserID);

	UserDto getActiveUserDtoByEmailAndPassword(String email, String password);
	
	UserDto getCurrentUserDtoByAuthentication(Authentication authentication);
	
	UserMinDto getCurrentUserMinDtoByAuthentication(Authentication authentication);

	void checkEmailExist(String email);

	void checkUsernameExist(String username);

}
