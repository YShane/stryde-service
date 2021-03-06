package com.stryde.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.stryde.webservice.dto.UserDto;

@Service
public class SecurityServiceImpl implements SecurityService{

	@Autowired
	private AuthService AuthService;
	
	@Override
	public boolean isMyAccount(Authentication authentication, Long userId) {
		UserDto loggedInUserDto = AuthService.getCurrentUserDtoByAuthentication(authentication);

		if (loggedInUserDto.getUserId() == userId) {
			return true;
		} else {
			return false;
		}
	}
	
}