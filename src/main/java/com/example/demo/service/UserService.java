package com.example.demo.service;

import com.example.demo.base.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    // 로그인
    ApiResponse<LoginResponse> login(LoginRequest request);

    // 친구 목록 조회 (로그인한 사용자 제외)
    ApiResponse<List<User>> getFriends(String userId);

}