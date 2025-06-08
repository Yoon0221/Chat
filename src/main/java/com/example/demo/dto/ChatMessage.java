package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String roomId;
    private String senderId;
    private String content;

    // 기본 생성자
    public ChatMessage() {}

    public ChatMessage(String roomId, String senderId, String content) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.content = content;
    }
    
}
