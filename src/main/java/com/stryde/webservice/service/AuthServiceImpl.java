package com.stryde.webservice.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.dto.auth.RegistrationDto;
import com.stryde.webservice.exception.AppErrorCode;
import com.stryde.webservice.exception.StrydeException;
import com.stryde.webservice.model.domain.User;
import com.stryde.webservice.model.enums.UserRole;
import com.stryde.webservice.model.enums.UserState;
import com.stryde.webservice.model.repository.UserRepository;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto registerUser(RegistrationDto registrationDto) {
		validateInput(registrationDto);
		
		com.stryde.webservice.model.domain.User user = new User();
		LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
		
		// user data
		user.setEmail(registrationDto.getEmail());
		user.setUsername(registrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		user.setDateOfBirth(registrationDto.getDateOfBirth());
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		
		// standard inactive, need to confirm email to get the account activated
		user.setState(UserState.INACTIVE);
		
		// set default role user
		user.setUserRole(UserRole.ROLE_USER);
		
		user = userRepository.save(user);

		// after user creation, user entity will have id
		user.getEditInfo().setCreatedOn(now);
		user.getEditInfo().setUpdatedOn(now);
		user.getEditInfo().setCreatedBy(user);
		user.getEditInfo().setUpdatedBy(user);
		user = userRepository.save(user);
		
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getActiveUserDtoByEmailAndPassword(String email, String password) {
		User user = userRepository.findByEmailAndState(email, UserState.ACTIVE)
				.orElseThrow(() -> new StrydeException(AppErrorCode.USER_NOT_FOUND));
		
		if (validatePassword(password, user)) {
			return modelMapper.map(user, UserDto.class);
		}else {
			throw new StrydeException(AppErrorCode.WRONG_CREDENTIAL);
		}
		
	}

	@Override
	public UserDto getCurrentUserByAuthentication(Authentication authentication) {
		String email = (String) authentication.getPrincipal();
		Optional<User> optUser = userRepository.findByEmail(email);
		return modelMapper.map(optUser.get(), UserDto.class);
	}
	
	private void validateInput(RegistrationDto registrationDto) {
		if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
			logger.error("email:{} is already exist", registrationDto.getEmail());
			throw new StrydeException(AppErrorCode.EMAIL_ALREADY_EXIST);
		}

		if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
			logger.error("username:{} is already exist", registrationDto.getUsername());
			throw new StrydeException(AppErrorCode.USERNAME_ALREADY_EXIST);
		}
	}
	
	private boolean validatePassword(String password, User user) {
		return passwordEncoder.matches(password, user.getPassword());
	}
}
