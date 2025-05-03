package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChatRoomListResponse {
    private List<ChatRoomData> chatRooms;

    @Getter
    @AllArgsConstructor
    public static class ChatRoomData {
        private String id;
        private String name;
    }
}