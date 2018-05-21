package com.stryde.webservice.service;

import java.time.Clock;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.stryde.webservice.exception.AuthException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.dto.UserMinDto;
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
		
		User user = new User();
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
	public UserDto registerFacebookUser(String accessToken, String username, String email, String facebookUserID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getActiveUserDtoByEmailAndPassword(String email, String password) {
		User user = userRepository.findByEmailAndState(email, UserState.ACTIVE)
				.orElseThrow(() -> new StrydeException(AppErrorCode.USER_NOT_FOUND));
		
		if (validatePassword(password, user)) {
			return modelMapper.map(user, UserDto.class);
		}else {
			throw new AuthException(AppErrorCode.WRONG_CREDENTIAL);
		}
		
	}

	@Override
	public UserDto getCurrentUserDtoByAuthentication(Authentication authentication) {
		String email = (String) authentication.getPrincipal();
		User user =  findUserByEmailOrThrow(email);
		return modelMapper.map(user, UserDto.class);
	}
	
	@Override
	public UserMinDto getCurrentUserMinDtoByAuthentication(Authentication authentication) {
		String email = (String) authentication.getPrincipal();
		User user =  findUserByEmailOrThrow(email);
		return modelMapper.map(user, UserMinDto.class);
	}
	
	@Override
	public void checkEmailExist(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			logger.error("email:{} is already exist", email);
			throw new StrydeException(AppErrorCode.EMAIL_ALREADY_EXIST);
		}
	}
	
	@Override
	public void checkUsernameExist(String username) {
		if (userRepository.findByUsername(username).isPresent()) {
			logger.error("username:{} is already exist", username);
			throw new StrydeException(AppErrorCode.USERNAME_ALREADY_EXIST);
		}
	}
	
	private User findUserByEmailOrThrow(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new StrydeException(AppErrorCode.USER_NOT_FOUND));
	}
	
	private void validateInput(RegistrationDto registrationDto) {
		checkEmailExist(registrationDto.getEmail());
		checkUsernameExist(registrationDto.getUsername());
	}
	
	private boolean validatePassword(String password, User user) {
		return passwordEncoder.matches(password, user.getPassword());
	}

}
