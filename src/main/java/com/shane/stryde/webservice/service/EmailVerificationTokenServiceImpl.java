package com.shane.stryde.webservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shane.stryde.webservice.dto.auth.EmailVerificationTokenDto;
import com.shane.stryde.webservice.model.domain.EmailVerificationToken;
import com.shane.stryde.webservice.model.domain.User;
import com.shane.stryde.webservice.model.enums.UserState;
import com.shane.stryde.webservice.model.repository.EmailVerificationTokenRepository;
import com.shane.stryde.webservice.model.repository.UserRepository;

@Service
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService{

	@Autowired
	private EmailVerificationTokenRepository emailVerificationTokenRepository;

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public EmailVerificationTokenDto save(EmailVerificationTokenDto emailVerificationTokenDto) {	
		Optional<User> optUser = userRepository.findById(emailVerificationTokenDto.getUserId());
		
		EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
		emailVerificationToken.setUser(optUser.get());
		emailVerificationToken.setToken(emailVerificationTokenDto.getToken());
		emailVerificationToken.setExpiryDate(emailVerificationTokenDto.getExpiryDate());
		emailVerificationTokenRepository.save(emailVerificationToken);
		
		return modelMapper.map(emailVerificationToken, EmailVerificationTokenDto.class);
	}

	@Override
	public boolean verifyToken(String token) {
		// try to get find token
		Optional<EmailVerificationToken> optEmailVerificationToken = emailVerificationTokenRepository.findByToken(token);
		
		// if found token
		if(optEmailVerificationToken.isPresent()) {
			// set user to active
			User user = optEmailVerificationToken.get().getUser();
			user.setState(UserState.ACTIVE);
			userRepository.save(user);
			return true;
		}
		return false;
	}

}
