package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRoomDetailsResponse {

    private String name;
    private List<Participant> participants; // Participant 객체로 변경

    public ChatRoomDetailsResponse(String name, List<Participant> participants) {
        this.name = name;
        this.participants = participants;
    }

    // Participant 클래스를 추가하여 참여자 정보를 포함
    @Getter
    @Setter
    public static class Participant {
        private String userId;
        private String name;

        public Participant(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }
    }
}