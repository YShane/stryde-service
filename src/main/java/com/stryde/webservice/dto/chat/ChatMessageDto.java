package com.stryde.webservice.dto.chat;

import com.stryde.webservice.dto.UserMinDto;

public class ChatMessageDto {

	private UserMinDto from;
	private String text;
	
	public UserMinDto getFrom() {
		return from;
	}
	public void setFrom(UserMinDto from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
