package com.stryde.webservice.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.stryde.webservice.model.domain.embedded.ChatParticipantPK;

@Entity
public class ChatParticipant implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ChatParticipantPK chatParticipantPK;
	
	@Column
	private LocalDateTime joinDate;

	public ChatParticipantPK getChatParticipantPK() {
		return chatParticipantPK;
	}

	public void setChatParticipantPK(ChatParticipantPK chatParticipantPK) {
		this.chatParticipantPK = chatParticipantPK;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}
}
