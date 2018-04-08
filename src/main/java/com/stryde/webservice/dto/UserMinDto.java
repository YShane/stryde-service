package com.stryde.webservice.dto;

/**
 * @author DANNY
 */
public class UserMinDto {
	
	private Long userId;
	
	private String username;
	
	public UserMinDto(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}