package org.example.chatbotservice.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatBotService {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    public ChatBotService(
            ChatClient.Builder builder,
            ChatMemory chatMemory,
            ToolCallbackProvider tools
            ) {

        this.chatClient = builder.defaultTools(tools).build();
        this.chatMemory = chatMemory;
    }

    public String chat(String request) {

        String conversationId = "default";

        String response = chatClient.prompt()
                .messages(chatMemory.get(conversationId))
                .user(request)
                .call()
                .content();

        // Store only text, without reasoning metadata
        chatMemory.add(conversationId, List.of(
                new UserMessage(request),
                new AssistantMessage(response)
        ));

        return response;
    }
}