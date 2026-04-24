package com.example.demo.service.impl;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.EntityExistsException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserResponse createUser(UserRequest request) {
		String email = request.getEmail();
		if (userRepository.existsUserByEmailIgnoreCase(email)) {
			throw new EntityExistsException("User already exists with email: " + email);
		}

		User user = new User();
		user.setFullName(request.getFullName());
		user.setEmail(email);
		User saved = userRepository.save(user);
		return new UserResponse(saved.getId(), saved.getFullName(), saved.getEmail());
	}

	@Override
	public UserResponse getUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId));
		return new UserResponse(user.getId(), user.getFullName(), user.getEmail());
	}
}
