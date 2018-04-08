package com.stryde.webservice.service;

import org.springframework.security.core.Authentication;

public interface SecurityService {

	public boolean isMyAccount(Authentication authentication, Long userId);

}