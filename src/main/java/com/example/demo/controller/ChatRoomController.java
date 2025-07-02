package com.example.demo.controller;

import com.example.demo.base.ApiResponse;
import com.example.demo.dto.ChatRoomCreateRequest;
import com.example.demo.dto.ChatRoomCreateResponse;
import com.example.demo.dto.ChatRoomDetailsResponse;
import com.example.demo.dto.ChatRoomListResponse;
import com.example.demo.entity.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import com.example.demo.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageRepository chatMessageRepository;

    @PostMapping("/create")
    public ApiResponse<ChatRoomCreateResponse> createChatRoom(@RequestBody ChatRoomCreateRequest request) {
        return chatRoomService.createChatRoom(request);
    }

    // 채팅방 목록을 가져오는 API
    @GetMapping("/list/{userId}")
    public ApiResponse<ChatRoomListResponse> getChatRooms(@PathVariable String userId) {
        return chatRoomService.getChatRoomsByUserId(userId);
    }

    // 특정 채팅방 세부 정보를 가져오는 API
    @GetMapping("/details/{chatRoomId}")
    public ApiResponse<ChatRoomDetailsResponse> getChatRoomDetails(@PathVariable String chatRoomId) {
        return chatRoomService.getChatRoomDetails(chatRoomId);
    }

    // 채팅방별 메시지 조회 API
    @GetMapping("/{roomId}/messages")
    public List<ChatMessage> getMessages(@PathVariable String roomId) {
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }

}