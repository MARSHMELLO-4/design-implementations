package com.aman.AbuseMasker.controller;


import com.aman.AbuseMasker.model.ChatMessage;
import com.aman.AbuseMasker.service.AbuseMaskerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    //create the bean of that
    private AbuseMaskerService abuseMaskerService;

    public ChatController(AbuseMaskerService abuseMaskerService){
        this.abuseMaskerService  = abuseMaskerService;
    }


    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message){

        String maskedMessage = abuseMaskerService.maskMessage(message.getContent());
        message.setContent(maskedMessage);

        return message;
    }

}
