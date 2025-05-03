package com.example.demo.repository;

import com.example.demo.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    // 사용자가 참여한 채팅방을 조회하는 메서드
    List<ChatRoom> findByParticipantsUserId(String userId);
}