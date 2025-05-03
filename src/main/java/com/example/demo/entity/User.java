package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Getter
@Setter
public class User {

    @Id
    private String id; // MongoDB의 고유 ID

    @Indexed(unique = true) // 유니크 제약 조건 추가
    private String userId; // 로그인용 사용자 아이디

    private String password; // 사용자 비밀번호

    private String name; // 사용자 이름

}