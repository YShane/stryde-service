package com.stryde.webservice.service;

import com.stryde.webservice.dto.auth.EmailVerificationTokenDto;

public interface EmailVerificationTokenService {

	EmailVerificationTokenDto save(EmailVerificationTokenDto emailVerificationTokenDto);
	boolean verifyToken(String token);
}
