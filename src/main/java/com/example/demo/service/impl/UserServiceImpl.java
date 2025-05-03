package com.example.demo.service.impl;

import com.example.demo.base.ApiResponse;
import com.example.demo.base.code.exception.CustomException;
import com.example.demo.base.status.ErrorStatus;
import com.example.demo.base.status.SuccessStatus;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ApiResponse<LoginResponse> login(LoginRequest request) {
        try {
            // 아이디와 비밀번호로 사용자 찾기
            User user = userRepository.findByUserIdAndPassword(request.getUserId(), request.getPassword())
                    .orElseThrow(() -> new CustomException(ErrorStatus.LOGIN_FAILED));

            // 로그인 성공 시 사용자 이름 반환
            LoginResponse response = new LoginResponse(user.getUserId(), user.getName());
            return ApiResponse.of(SuccessStatus.USER_LOGIN_SUCCESS, response);

        } catch (Exception e) {
            // 다른 예외 발생 시 처리
            throw new CustomException(ErrorStatus.COMMON_BAD_REQUEST);
        }
    }

    @Override
    public ApiResponse<List<User>> getFriends(String userId) {
        try {
            // 로그인한 사용자를 제외한 모든 사용자 조회
            List<User> friends = userRepository.findByUserIdNot(userId);
            return ApiResponse.of(SuccessStatus.FRIENDS_FETCH_SUCCESS, friends);
        } catch (Exception e) {
            // 예외 발생 시 처리
            throw new CustomException(ErrorStatus.COMMON_BAD_REQUEST);
        }
    }

}