package com.example.demo.controller;

import com.example.demo.base.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인 처리
    @PostMapping("/login")
    @ResponseBody
    public ApiResponse<LoginResponse> login(@ModelAttribute LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    // 친구 목록 조회
    @GetMapping("/friends")
    @ResponseBody
    public ApiResponse<List<User>> getFriends(@RequestParam String userId) {
        return userService.getFriends(userId);
    }

}