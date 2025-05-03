package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String userId; // 사용자 아이디
    private String password; // 사용자 비밀번호
}