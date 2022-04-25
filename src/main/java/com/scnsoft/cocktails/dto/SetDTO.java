package com.scnsoft.cocktails.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.scnsoft.cocktails.entity.Set;
import com.scnsoft.cocktails.entity.SetStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class SetDTO {
	private UUID id;
	
	private UserDTO owner;
	
	private LocalDate date;
	
	private String password;
	
	private SetStatus status;
	
	private List<UserDTO> users;
	
	private List<CocktailDTO> cocktails;
	
	public SetDTO(Set set) {
		id = set.getId();
		owner = new UserDTO(set.getOwner());
		date = set.getDate();
		password = null;
		status = set.getStatus();
		users = set
			.getUsers()
			.stream()
			.map(u -> new UserDTO(u))
			.toList();
		cocktails = set
			.getCocktails()
			.stream()
			.map(c -> new CocktailDTO(c, false, false))
			.toList();
	}
}
