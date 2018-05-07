package com.stryde.webservice.model.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Chat implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chatId;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="chat")
    Collection<ChatMessage> chatMessages;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="chatParticipantPK.chat")
    Collection<ChatParticipant> chatParticipants;

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Collection<ChatMessage> getChatMessages() {
		return chatMessages;
	}

	public void setChatMessages(Collection<ChatMessage> chatMessages) {
		this.chatMessages = chatMessages;
	}

	public Collection<ChatParticipant> getChatParticipants() {
		return chatParticipants;
	}

	public void setChatParticipants(Collection<ChatParticipant> chatParticipants) {
		this.chatParticipants = chatParticipants;
	}
}
