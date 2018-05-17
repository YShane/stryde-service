package com.stryde.webservice.service;

import com.stryde.webservice.dto.chat.AllChatsDto;
import com.stryde.webservice.model.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl {

    private ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public AllChatsDto getAllChatsForUser(Long userId){
        List<Object[]> res = this.chatRepository.findallChatsforUserwithId(userId);
        return null;
    }
}
