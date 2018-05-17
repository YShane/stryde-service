package com.stryde.webservice.controller.rest.secured;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatRestController {

    @PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
    @PostMapping("/allchats")
    public ResponseEntity<?> getAllChats(){
        return null;
    }
}
