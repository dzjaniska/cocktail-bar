package com.scnsoft.cocktails.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.UserRepository;
import com.scnsoft.cocktails.entity.User;
import com.scnsoft.cocktails.entity.UserRole;

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

	public User save(User entity) {
		int atIndex = entity.getLogin().indexOf("@");
		entity.setTitle(atIndex == -1 ? entity.getLogin() : entity.getLogin().substring(0, atIndex));
		
		entity.setRole(UserRole.USER);
		
		return userRepository.save(entity);
	}

	public User update(User entity) {
		return userRepository.save(entity);
	}

	public void deleteById(UUID userId) {
		userRepository.deleteById(userId);
	}
}
