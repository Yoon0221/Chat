package com.example.demo.service.impl;

import com.example.demo.base.ApiResponse;
import com.example.demo.base.code.exception.CustomException;
import com.example.demo.base.status.ErrorStatus;
import com.example.demo.base.status.SuccessStatus;
import com.example.demo.dto.ChatRoomCreateRequest;
import com.example.demo.dto.ChatRoomCreateResponse;
import com.example.demo.dto.ChatRoomDetailsResponse;
import com.example.demo.dto.ChatRoomListResponse;
import com.example.demo.entity.ChatRoom;
import com.example.demo.entity.User;
import com.example.demo.repository.ChatRoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<ChatRoomCreateResponse> createChatRoom(ChatRoomCreateRequest request) {
        try {
            log.info("채팅방 생성 요청: {}", request);

            // 주최자 ID
            String hostId = request.getHostId();

            // 요청에서 유저 ID 목록을 가져오고, 주최자 ID를 포함하여 중복 제거
            Set<String> userIdSet = new HashSet<>(request.getUserIds());
            userIdSet.add(hostId); // 주최자 자동 포함

            // 유저 ID 목록을 기반으로 사용자 정보 조회
            log.info("조회할 userIds: {}", userIdSet);

            List<ChatRoom.UserInfo> participants = userRepository.findByUserIdIn(userIdSet).stream()
                    .map(user -> new ChatRoom.UserInfo(user.getUserId(), user.getName()))
                    .collect(Collectors.toList());


            log.info("조회된 참여자 목록: {}", participants);

            // 참여자가 없는 경우 예외 발생
            if (participants.isEmpty()) {
                log.warn("채팅방 생성 실패: 참여자 없음");
                throw new CustomException(ErrorStatus.CHATROOM_PARTICIPANTS_NOT_FOUND);
            }

            log.info("채팅방 '{}' 생성, 주최자 '{}' 포함된 참여자 목록: {}", request.getName(), hostId, participants);

            // 채팅방 엔터티 생성 및 저장
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setName(request.getName());
            chatRoom.setParticipants(participants);
            ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

            log.info("채팅방 생성 성공: {}", savedChatRoom);

            // 응답 객체 생성 및 반환
            ChatRoomCreateResponse response = new ChatRoomCreateResponse(
                    savedChatRoom.getId(),
                    savedChatRoom.getName(),
                    savedChatRoom.getParticipants()
            );

            return ApiResponse.of(SuccessStatus.CHATROOM_CREATE_SUCCESS, response);
        } catch (CustomException e) {
            log.error("채팅방 생성 중 CustomException 발생: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("채팅방 생성 중 알 수 없는 예외 발생", e);
            throw new CustomException(ErrorStatus.COMMON_BAD_REQUEST);
        }
    }

    @Override
    public ApiResponse<ChatRoomListResponse> getChatRoomsByUserId(String userId) {
        try {
            List<ChatRoom> chatRooms = chatRoomRepository.findByParticipantsUserId(userId);

            if (chatRooms.isEmpty()) {
                log.warn("채팅방 조회 실패: 해당 사용자의 채팅방 없음");
                throw new CustomException(ErrorStatus.CHATROOM_PARTICIPANTS_NOT_FOUND); // 채팅방이 없으면 예외 발생
            }

            // 채팅방이 존재하면, 채팅방 정보를 반환
            List<ChatRoomListResponse.ChatRoomData> chatRoomDataList = chatRooms.stream()
                    .map(chatRoom -> new ChatRoomListResponse.ChatRoomData(chatRoom.getId(), chatRoom.getName()))
                    .collect(Collectors.toList());

            ChatRoomListResponse response = new ChatRoomListResponse(chatRoomDataList);

            return ApiResponse.of(SuccessStatus._OK, response); // 채팅방 목록 반환
        } catch (CustomException e) {
            log.error("채팅방 조회 중 CustomException 발생: {}", e.getMessage());
            throw e; // CustomException 발생 시 다시 던짐
        } catch (Exception e) {
            log.error("채팅방 조회 중 알 수 없는 예외 발생", e);
            throw new CustomException(ErrorStatus.COMMON_BAD_REQUEST); // 일반 예외는 CustomException으로 처리
        }
    }

    @Override
    public ApiResponse<ChatRoomDetailsResponse> getChatRoomDetails(String chatRoomId) {
        try {
            // 채팅방 정보 조회
            ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                    .orElseThrow(() -> new CustomException(ErrorStatus.CHATROOM_PARTICIPANTS_NOT_FOUND)); // 채팅방이 없으면 예외 발생

            // 참여자 정보 조회
            List<String> participantIds = chatRoom.getParticipants().stream()
                    .map(ChatRoom.UserInfo::getUserId)
                    .collect(Collectors.toList());

            // Set을 사용하여 유저 정보를 조회
            List<User> users = userRepository.findByUserIdIn(new HashSet<>(participantIds));

            // 참여자 정보 리스트 생성
            List<ChatRoomDetailsResponse.Participant> participantDetails = users.stream()
                    .map(user -> new ChatRoomDetailsResponse.Participant(user.getUserId(), user.getName()))
                    .collect(Collectors.toList());

            // 채팅방 세부 정보 반환
            ChatRoomDetailsResponse response = new ChatRoomDetailsResponse(chatRoom.getName(), participantDetails);

            return ApiResponse.of(SuccessStatus._OK, response); // 채팅방 세부 정보 반환

        } catch (Exception e) {
            log.error("채팅방 세부 정보 조회 중 예외 발생", e);
            throw new CustomException(ErrorStatus.COMMON_BAD_REQUEST); // 일반 예외 처리
        }
    }


}