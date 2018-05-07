package com.stryde.webservice.model.domain.embedded;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.stryde.webservice.model.domain.ChatMessage;
import com.stryde.webservice.model.domain.User;

@Embeddable
public class ChatMessageUserPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	private ChatMessage chatMessage;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ChatMessage getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(ChatMessage chatMessage) {
		this.chatMessage = chatMessage;
	}
}
