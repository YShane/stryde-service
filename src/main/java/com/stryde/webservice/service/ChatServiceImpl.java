package com.stryde.webservice.service;

import com.stryde.webservice.dto.chat.AllChatsDto;
import com.stryde.webservice.dto.chat.ChatMinDto;
import com.stryde.webservice.model.domain.Chat;
import com.stryde.webservice.model.domain.User;
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

    private UserService userService;
    private ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(UserService userService, ChatRepository chatRepository){
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    @Override
    public AllChatsDto getAllChatsForUser(Long userId){
        List<ChatRepository.ChatDetails> res = this.chatRepository.getallChatsforUserwithId(userId);

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
        AllChatsDto chatsDto = new AllChatsDto();
        chatsDto.setChats(chats);

        return chatsDto;
    }

    @Override
    public void createChatwithOtherUserwithId(Long userId) {

        Chat newChat = new Chat();

    }
}
