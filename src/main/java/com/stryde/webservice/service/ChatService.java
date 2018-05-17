package com.stryde.webservice.service;

import com.stryde.webservice.dto.chat.AllChatsDto;

public interface ChatService {

    AllChatsDto getAllChatsForUser(Long userId);
}
