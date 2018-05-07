package com.stryde.webservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
    /**
     * a simple memory-based message broker to carry the messages back to the client on destinations prefixed with “/queue”.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app")
        	  .enableSimpleBroker("/chat");
    }
 
    /** 
     * registered stomp endpoints at “/messages-endpoint”. In case that we want to enable SockJS, we have to amend the register part:
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
         registry.addEndpoint("/ws")
		         .setAllowedOrigins("*")
		         .withSockJS();
    }
}