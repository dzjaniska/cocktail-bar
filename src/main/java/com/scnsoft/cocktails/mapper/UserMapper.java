package com.scnsoft.cocktails.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.dao.UserRepository;
import com.scnsoft.cocktails.dto.UserDTO;
import com.scnsoft.cocktails.entity.User;

@Component
public class UserMapper {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	public User toEntity(UserDTO dto, boolean setLogin, boolean setPassword) {
        if (dto == null) {
            return null;
        }
        User user;
        if (dto.getId() == null) {
            user = new User();
        } else {
            user = userRepository.getById(dto.getId());
        }
        user.setTitle(dto.getTitle());
        if (setLogin) {
			user.setLogin(dto.getLogin());
		}
		if (setPassword) {
			String encodedPassword = passwordEncoder.encode(dto.getPassword());
			user.setPassword(encodedPassword);
		}
		user.setRole(dto.getRole());
       
        return user;
    }
}
