package com.stryde.webservice.dto;

import java.io.Serializable;

public class LoginFacebookDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String accessToken;
	private String username ;
	private String email;
	private String facebookUserID;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacebookUserID() {
		return facebookUserID;
	}
	public void setFacebookUserID(String facebookUserID) {
		this.facebookUserID = facebookUserID;
	}
}
