package com.stryde.webservice.service;

import javax.mail.MessagingException;

public interface EmailService {

	/**
	 * send email from no reply address (evms automated email) to user
	 * @param to
	 * @param subject
	 * @param htmlMessage
	 * @throws MessagingException
	 */
	void sendNoReplyHtmlMessage(String to, String subject, String htmlMessage) throws MessagingException;
	
	
	/**
	 * Method to send email
	 * @param to
	 * @param from
	 * @param subject
	 * @param htmlMessage
	 * @throws MessagingException
	 */
	void sendHtmlMessage(String to, String from, String subject, String htmlMessage) throws MessagingException;
}
