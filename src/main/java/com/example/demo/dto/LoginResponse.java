package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String userId;
    private String name;

    public LoginResponse(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}