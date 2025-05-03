package com.example.demo.service;

import com.example.demo.base.ApiResponse;
import com.example.demo.dto.*;
import com.example.demo.dto.ChatRoomListResponse;

public interface ChatRoomService {

    // 채팅방 생성하기
    ApiResponse<ChatRoomCreateResponse> createChatRoom(ChatRoomCreateRequest request);

    // 사용자 ID로 채팅방 목록을 가져오는 메서드
    ApiResponse<ChatRoomListResponse> getChatRoomsByUserId(String userId);

    // 채팅방 세부 정보 가져오기
    ApiResponse<ChatRoomDetailsResponse> getChatRoomDetails(String chatRoomId);

}