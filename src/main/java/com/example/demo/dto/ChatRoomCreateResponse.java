package com.example.demo.dto;

import com.example.demo.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class ChatRoomCreateResponse {
    private String id;                          // 채팅방 ID
    private String name;                        // 채팅방 이름
    private List<ChatRoom.UserInfo> participants; // 참가자 정보
}