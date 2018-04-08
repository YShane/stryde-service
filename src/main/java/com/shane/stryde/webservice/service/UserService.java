package com.shane.stryde.webservice.service;

import com.shane.stryde.webservice.dto.UserDto;

public interface UserService {

	public UserDto getUser(Long userId);

}