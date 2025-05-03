package com.example.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Document(collection = "chatRoom")
@Getter
@Setter
public class ChatRoom {
    @Id
    private String id; // MongoDB 고유 ID

    private String name; // 채팅방 이름

    private List<UserInfo> participants; // 참여자 정보 리스트

    @Getter
    @Setter
    public static class UserInfo {
        private String userId;
        private String name;

        public UserInfo(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }
    }

}