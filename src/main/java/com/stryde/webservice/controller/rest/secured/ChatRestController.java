package com.stryde.webservice.controller.rest.secured;

import com.stryde.webservice.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat")
public class ChatRestController {

    private ChatService chatService;

    public ChatRestController(ChatService chatService){
        this.chatService = chatService;
    }

    @PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
    @GetMapping("/newchat")
    public void newChat(Long userId){

    }


    //post request because more parameters likely to be added in the future
    @PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
    @PostMapping("/allchats")
    public ResponseEntity<?> getAllChats(@Valid @RequestBody Long userId){
        return new ResponseEntity<>(chatService.getAllChatsForUser(userId), HttpStatus.OK);
    }
}
