package com.stryde.webservice.service;

import com.stryde.webservice.dto.chat.AllChatsDto;
import org.springframework.stereotype.Service;

@Service
public interface ChatService {

    AllChatsDto getAllChatsForUser(Long userId);

    void createChatwithOtherUserwithId(Long userId);
}
