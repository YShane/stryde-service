package com.shane.stryde.webservice.service;

import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

import com.shane.stryde.webservice.dto.UserDto;

public interface AccountService {

	public UserDto updateUser(UserDto userDto) throws ParseException;

	public void updateProfilePicture(MultipartFile multipartFile);
}
