package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

import java.util.List;
import java.util.Set;

public interface UserRepository extends MongoRepository<User, String> {

    // 아이디와 비밀번호로 사용자 찾기
    Optional<User> findByUserIdAndPassword(String userId, String password);

    // 주어진 사용자 ID를 제외한 모든 사용자 조회
    List<User> findByUserIdNot(String userId);

    // 주어진 사용자 ID 목록에 해당하는 사용자 조회
    List<User> findByUserIdIn(Set<String> userIds);

}