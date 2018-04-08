package com.stryde.webservice.scheduled.listener;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

import javax.mail.MessagingException;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.dto.auth.EmailVerificationTokenDto;
import com.stryde.webservice.scheduled.event.OnRegistrationCompleteEvent;
import com.stryde.webservice.service.EmailService;
import com.stryde.webservice.service.EmailVerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.dto.auth.EmailVerificationTokenDto;
import com.stryde.webservice.scheduled.event.OnRegistrationCompleteEvent;
import com.stryde.webservice.service.EmailService;
import com.stryde.webservice.service.EmailVerificationTokenService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class);

	@Autowired
	private EmailService emailService;

	@Value("${base.url}")
	private String baseUrl;

	@Value("${server.servlet.contextPath}")
	private String serverContextPath;

	@Value("${server.port}")
	private String serverPort;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private EmailVerificationTokenService emailVerificationTokenService;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		try {
			sendActivationEmail(event);
		} catch (MessagingException e) {
			LOGGER.error("failed to send activation email for email address:{}", event.getUserDto().getEmail());
		}
	}

	private void sendActivationEmail(OnRegistrationCompleteEvent event) throws MessagingException {
		UserDto userDto = event.getUserDto();
		String token = UUID.randomUUID().toString();

		EmailVerificationTokenDto emailVerificationTokenDto = new EmailVerificationTokenDto();
		emailVerificationTokenDto.setUserId(userDto.getUserId());
		emailVerificationTokenDto.setToken(token);
		emailVerificationTokenDto.setExpiryDate(LocalDateTime.now(Clock.systemUTC()));
		emailVerificationTokenService.save(emailVerificationTokenDto);
		
		String recipientAddress = userDto.getEmail();
		String url = baseUrl + serverContextPath + "/auth/verify/" + token;
		
		// TODO check if its working
		Locale locale = LocaleContextHolder.getLocaleContext().getLocale();
		String subject = messageSource.getMessage(
			       "email.registration.subject", 
			       new Object[]{}, 
			       locale);
		
		String htmlMessage = messageSource.getMessage(
			       "email.registration.message", 
			       new Object[]{url}, 
			       locale);

		emailService.sendNoReplyHtmlMessage(recipientAddress, subject, htmlMessage);
	}
}
