package com.example.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Document(collection = "chatMessage")
@Getter
@Setter
public class ChatMessage {

    @Id
    private String id; // MongoDB의 고유 ID

    private String roomId;
    private String senderId;
    private String senderName;
    private String content;
    private LocalDateTime timestamp;

}