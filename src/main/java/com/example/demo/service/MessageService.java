package com.example.demo.service;

import com.example.demo.entity.base.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(Message message) {
        // 메시지를 MongoDB에 저장
        messageRepository.save(message);
    }

    // 메시지 또는 채팅 기록을 가져오는 다른 메서드들
}
