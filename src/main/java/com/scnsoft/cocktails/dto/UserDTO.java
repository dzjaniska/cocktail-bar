package com.scnsoft.cocktails.dto;

import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.scnsoft.cocktails.entity.User;
import com.scnsoft.cocktails.entity.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class UserDTO {
	private UUID id;
	
	private String title;
	
	private String login;
	
	private String password;
	
	private UserRole role;
	
	public UserDTO(User user) {
		id = user.getId();
		title = user.getTitle();
		login = null;
		password = null;
		role = user.getRole();
	}

}
