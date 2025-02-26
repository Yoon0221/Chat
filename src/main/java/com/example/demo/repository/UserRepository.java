package com.example.demo.repository;

import com.example.demo.entity.base.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}