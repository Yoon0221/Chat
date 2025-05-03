package com.example.demo.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ChatRoomCreateRequest {
    private String name;          // 채팅방 이름
    private String hostId;        // 주최자 아이디
    private List<String> userIds; // 참가자 ID 목록 (주최자 제외한 참가자들)
}