package com.stryde.webservice.dto.chat;

import java.util.ArrayList;
import java.util.List;

public class AllChatsDto {

    private List<ChatMinDto> chats = new ArrayList<>();

    public List<ChatMinDto> getChats() {
        return chats;
    }

    public void setChats(List<ChatMinDto> chats) {
        this.chats = chats;
    }
}
