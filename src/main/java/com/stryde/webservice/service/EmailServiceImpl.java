package com.stryde.webservice.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${spring.mail.username}")
	private String noReplyAddress;

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendHtmlMessage(String to, String from, String subject, String htmlMessage) throws MessagingException {
		sendMime(to, from, subject, htmlMessage);
	}

	@Override
	public void sendNoReplyHtmlMessage(String to, String subject, String htmlMessage) throws MessagingException {
		sendMime(to, noReplyAddress, subject, htmlMessage);
	}

	private void sendMime(String to, String from, String subject, String htmlMessage) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		mimeMessage.setContent(htmlMessage, "text/html");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setFrom(from);
		emailSender.send(mimeMessage);
	}
}
