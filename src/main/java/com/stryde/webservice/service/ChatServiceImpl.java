package com.stryde.webservice.service;

import com.stryde.webservice.dto.chat.AllChatsDto;
import com.stryde.webservice.dto.chat.ChatMinDto;
import com.stryde.webservice.model.repository.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);

    private ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    @Override
    public AllChatsDto getAllChatsForUser(Long userId){
        List<ChatRepository.ChatDetails> res = this.chatRepository.getallChatsforUserwithId(userId);

        AllChatsDto chatsDto = new AllChatsDto();

        List<ChatMinDto> chats = new ArrayList<>();

        for(ChatRepository.ChatDetails chat: res){
            ChatMinDto chatMinDto = new ChatMinDto();
            chatMinDto.setUserId(chat.getUserid());
            chatMinDto.setUserfirstname(chat.getUserFirstname());
            chatMinDto.setUserlastname(chat.getUserlastname());
            chatMinDto.setUsername(chat.getUsername());
            chatMinDto.setProfilephotolink(chat.getProfilephotolink());
            chatMinDto.setDdob(chat.getUserdob());
            chats.add(chatMinDto);
        }
        chatsDto.setChats(chats);

        return chatsDto;
    }

    @Override
    public void createNewChatForUserWithId(Long userId) {

    }
}
