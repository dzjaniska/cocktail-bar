package com.scnsoft.cocktails.facade;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.dto.UserDTO;
import com.scnsoft.cocktails.entity.User;
import com.scnsoft.cocktails.mapper.UserMapper;
import com.scnsoft.cocktails.service.UserService;

@Transactional
@Controller
public class UserFacade {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	public Page<UserDTO> findAll(Pageable pageable) {
		
		Page<User> page = userService
				.findAll(pageable);
		
		return new PageImpl<>(page
				.stream()
				.map(u -> new UserDTO(u))
				.toList(),
				pageable, 
				page.getTotalElements());
	}

	public UserDTO findById(UUID userId) {
		
		return new UserDTO(userService.findById(userId));
	}

}
