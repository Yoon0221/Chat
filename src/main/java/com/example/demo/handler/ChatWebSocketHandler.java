package com.example.demo.handler;

import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.CloseStatus;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();  // 연결된 클라이언트 저장

    @Autowired
    private MessageService messageService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);  // 새로운 클라이언트 연결 시 세션 추가
        System.out.println("새로운 연결: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();  // 메시지 내용

        // 받은 메시지를 DB에 저장
        Message msg = new Message();
        msg.setContent(payload);
        msg.setTimestamp(new java.util.Date());
        messageService.sendMessage(msg);

        // 모든 연결된 클라이언트에게 메시지 전송
        for (WebSocketSession sess : sessions) {
            if (sess.isOpen()) {
                try {
                    sess.sendMessage(new TextMessage(payload));  // 메시지 전송
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);  // 클라이언트 연결 종료 시 세션 제거
        System.out.println("연결 종료: " + session.getId());
    }
}
