package com.example.demo.handler;

import com.example.demo.entity.base.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private MessageService messageService;

    @Override
    public void handleTextMessage(WebSocketSession session, org.springframework.web.socket.TextMessage message) throws Exception {
        // 클라이언트로부터 받은 메시지 내용
        String payload = message.getPayload();

        // 메시지 객체로 변환 (예시: JSON 파싱)
        Message msg = parseMessage(payload);

        // MongoDB에 메시지 저장
        messageService.sendMessage(msg);

        // 다른 클라이언트에게 메시지 전송 (브로드캐스트)
        broadcastMessageToOthers(session, payload);
    }

    private Message parseMessage(String payload) {
        // 메시지 파싱 로직 (예시)
        Message msg = new Message();
        msg.setSenderId("user1");
        msg.setReceiverId("user2");
        msg.setContent(payload);
        msg.setTimestamp(new java.util.Date());
        return msg;
    }

    private void broadcastMessageToOthers(WebSocketSession session, String message) {
        // 다른 클라이언트들에게 메시지 전송 (브로드캐스트)
    }
}
