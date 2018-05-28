package com.stryde.webservice.service;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.model.domain.User;

public interface UserService {

	public UserDto getUserDto(Long userId);

	public User getUser(Long userId);

}