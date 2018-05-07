package com.stryde.webservice.controller.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import com.stryde.webservice.dto.UserMinDto;
import com.stryde.webservice.service.AuthService;

@Controller
public class MessageController {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private AuthService authService;
	
	@MessageMapping("/message/{chatId}")
	public void sendChatMessageToChatWithChatId(@DestinationVariable Long chatId, @Payload String message, Authentication authentication) {
		//TODO check if user is participants of that chatId
		UserMinDto userMinDto = authService.getCurrentUserMinDtoByAuthentication(authentication);
		this.template.convertAndSend("/chat/"+chatId, message);  
	}
}
