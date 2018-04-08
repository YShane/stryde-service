package com.shane.stryde.webservice.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Value("${spring.mail.host}")
	private String emailHost;
	
	@Value("${spring.mail.port}")
	private int emailHostPort;
	
	@Value("${spring.mail.username}")
	private String emailUsername;
	
	@Value("${spring.mail.password}")
	private String emailPassword;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String emailSmtpAuth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String emailStartTlsEnable;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(emailHost);
	    mailSender.setPort(emailHostPort);
	    mailSender.setUsername(emailUsername);
	    mailSender.setPassword(emailPassword);
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", emailSmtpAuth);
	    props.put("mail.smtp.starttls.enable", emailStartTlsEnable);
	    props.put("mail.debug", "true");
	    // need to trust gmail ssl
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	     
	    return mailSender;
	}
}
