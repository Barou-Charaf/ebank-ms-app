package org.example.chatbotservice.web;

import jakarta.ws.rs.QueryParam;
import org.example.chatbotservice.services.ChatBotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatBotService chatBotService;

    public ChatController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping("/ask")
    public String ask(@QueryParam(value = "query") String query){
        return chatBotService.chat(query);
    }
}
