package com.shane.stryde.webservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shane.stryde.webservice.dto.UserDto;
import com.shane.stryde.webservice.model.domain.User;
import com.shane.stryde.webservice.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserDto getUser(Long userId) {
		Optional<User> optUser = userRepository.findById(userId);
		return modelMapper.map(optUser.get(), UserDto.class);
	}
}