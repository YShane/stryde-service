package com.stryde.webservice.service;

import java.io.IOException;
import java.text.ParseException;

import com.stryde.webservice.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import com.stryde.webservice.dto.UserDto;

public interface AccountService {

	public UserDto updateUser(UserDto userDto) throws ParseException;

	public void updateProfilePicture(UserDto userDto, MultipartFile multipartFile) throws IOException;
}
