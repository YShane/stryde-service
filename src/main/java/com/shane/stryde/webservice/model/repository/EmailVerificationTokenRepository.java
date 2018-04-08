package com.shane.stryde.webservice.model.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shane.stryde.webservice.model.domain.EmailVerificationToken;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Serializable>{

	Optional<EmailVerificationToken> findByToken(String token);
}