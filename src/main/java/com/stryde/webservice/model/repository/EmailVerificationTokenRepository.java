package com.stryde.webservice.model.repository;

import java.io.Serializable;
import java.util.Optional;

import com.stryde.webservice.model.domain.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stryde.webservice.model.domain.EmailVerificationToken;

public interface EmailVerificationTokenRepository extends BaseRepository<EmailVerificationToken, Serializable>{

	Optional<EmailVerificationToken> findByToken(String token);
}