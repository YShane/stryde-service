package com.stryde.webservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.model.domain.User;
import com.stryde.webservice.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserDto getUserDto(Long userId) {
		Optional<User> optUser = userRepository.findById(userId);
		return modelMapper.map(optUser.get(), UserDto.class);
	}

	public User getUser(Long userId){
		Optional<User> optUser = userRepository.findById(userId);
		return optUser.get();
	}

}