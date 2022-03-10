package com.scnsoft.cocktails.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.UserRepository;
import com.scnsoft.cocktails.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public User findById(UUID userId) {
		return userRepository.getById(userId);
	}

}
