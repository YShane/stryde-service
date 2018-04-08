package com.shane.stryde.webservice.dto.auth;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EmailVerificationTokenDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String token = new String();

	private Long userId;

	private LocalDateTime expiryDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

}
