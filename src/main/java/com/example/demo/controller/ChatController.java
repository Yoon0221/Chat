package com.example.demo.controller;

import com.example.demo.dto.ChatMessageDto;
import com.example.demo.entity.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageRepository chatMessageRepository) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/chat/send")  // 클라이언트의 /pub/chat/send 와 매핑
    public void sendMessage(ChatMessageDto message) {
        // 1) DB에 저장
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(message.getRoomId());
        chatMessage.setSenderId(message.getSenderId());
        chatMessage.setSenderName(message.getSenderName());
        chatMessage.setContent(message.getContent());
        chatMessage.setTimestamp(LocalDateTime.now());

        chatMessageRepository.save(chatMessage);

        // 2) 구독자에게 메시지 브로드캐스트
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

}