package com.example.demo.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "messages")
@Getter
@Setter
public class Message {
    @Id
    private String id;
    private String senderId;
    private String receiverId;  // 1:1 대화를 위한 필드
    private String groupId;  // 단체방 대화를 위한 필드
    private String content;
    private Date timestamp;

}