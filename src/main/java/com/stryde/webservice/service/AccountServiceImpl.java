package com.stryde.webservice.service;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.AppErrorCode;
import com.stryde.webservice.model.domain.User;
import com.stryde.webservice.model.repository.UserRepository;
import com.stryde.webservice.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.exception.AppErrorCode;
import com.stryde.webservice.exception.StrydeException;
import com.stryde.webservice.model.domain.User;
import com.stryde.webservice.model.repository.UserRepository;
import com.stryde.webservice.utils.DateUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StorageService storageService;

	@Transactional
	@Override
	public UserDto updateUser(UserDto userDto) throws ParseException {
		User user = userRepository.findById(userDto.getUserId())
				.orElseThrow(() -> new StrydeException(AppErrorCode.USER_NOT_FOUND));

		user = updateUser(userDto, user, DateUtils.getLocalDateTimeUtc());
		user = userRepository.save(user);

		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void updateProfilePicture(UserDto userDto, MultipartFile multipartFile) throws IOException {
		 storageService.storeProfilePicture(userDto, multipartFile);
	}

	private User updateUser(UserDto userDto, User user, LocalDateTime now) throws ParseException {
		user.getEditInfo().setUpdatedBy(user);
		user.getEditInfo().setUpdatedOn(now);

		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		return user;
	}
}
