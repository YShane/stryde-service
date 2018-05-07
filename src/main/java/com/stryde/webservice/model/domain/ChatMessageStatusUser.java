package com.stryde.webservice.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.stryde.webservice.model.domain.embedded.ChatMessageUserPK;
import com.stryde.webservice.model.enums.ChatMessageStatus;

@Entity
public class ChatMessageStatusUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ChatMessageUserPK chatMessageUserPK;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('UNREAD', 'READ', 'NO_STATUS')", nullable = false)
	private ChatMessageStatus chatMessageStatus;

	public ChatMessageUserPK getChatMessageUserPK() {
		return chatMessageUserPK;
	}

	public void setChatMessageUserPK(ChatMessageUserPK chatMessageUserPK) {
		this.chatMessageUserPK = chatMessageUserPK;
	}

	public ChatMessageStatus getChatMessageStatus() {
		return chatMessageStatus;
	}

	public void setChatMessageStatus(ChatMessageStatus chatMessageStatus) {
		this.chatMessageStatus = chatMessageStatus;
	}
}
