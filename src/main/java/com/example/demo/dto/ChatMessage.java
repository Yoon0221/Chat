package com.example.demo.dto;

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

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
