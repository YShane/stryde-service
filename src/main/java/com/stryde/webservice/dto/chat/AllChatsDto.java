package com.stryde.webservice.dto.chat;

import java.util.ArrayList;
import java.util.List;
//To be returned instead of list of chats. Contains a list of chats :)
public class AllChatsDto {

    private List<ChatMinDto> chats = new ArrayList<>();

    public List<ChatMinDto> getChats() {
        return chats;
    }

    public void setChats(List<ChatMinDto> chats) {
        this.chats = chats;
    }
}
