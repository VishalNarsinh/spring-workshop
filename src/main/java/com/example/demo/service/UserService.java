package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;

public interface UserService {

	UserResponse createUser(UserRequest request);

	UserResponse getUserById(Long userId);
}
