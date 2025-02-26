package com.example.demo.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "chatrooms")
@Getter
@Setter
public class ChatRoom {
    @Id
    private String id;
    private String name;
    private String type;  // "group" 또는 "private"
    private List<String> participants;

}