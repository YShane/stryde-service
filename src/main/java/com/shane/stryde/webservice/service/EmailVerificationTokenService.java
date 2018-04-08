package com.shane.stryde.webservice.service;

import com.shane.stryde.webservice.dto.auth.EmailVerificationTokenDto;

public interface EmailVerificationTokenService {

	EmailVerificationTokenDto save(EmailVerificationTokenDto emailVerificationTokenDto);
	boolean verifyToken(String token);
}
