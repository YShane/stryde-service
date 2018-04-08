package com.shane.stryde.webservice.scheduled.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.shane.stryde.webservice.dto.UserDto;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private Locale locale;
	private UserDto userDto;

	public OnRegistrationCompleteEvent(UserDto userDto, Locale locale) {
		super(userDto);

		this.userDto = userDto;
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

}
