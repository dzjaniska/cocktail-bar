package com.scnsoft.cocktails.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.dao.SetRepository;
import com.scnsoft.cocktails.dao.UserRepository;
import com.scnsoft.cocktails.dto.CreateSetDTO;
import com.scnsoft.cocktails.dto.SetDTO;
import com.scnsoft.cocktails.entity.Set;

@Component
public class SetMapper {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CocktailMapper cocktailMapper;
	
	@Autowired
	private SetRepository setRepository;
	
	public Set toEntity(CreateSetDTO dto) {
		
		if(dto == null) {
            return null;
        }
		
		Set set = new Set();
		set.setDate(dto.getDate());
		set.setPassword(dto.getPassword());
		set.setUsers(new ArrayList<>());
		set.setCocktails(new ArrayList<>());
		
		return set;
	}

	public Set toEntity(SetDTO dto) {
		
		if (dto == null) {
            return null;
        }
		
		Set set;
		if (dto.getId() == null)
			set = new Set();
		else
			set = setRepository.getById(dto.getId());			
		
		set.setOwner(userMapper.toEntity(dto.getOwner(), false, false));
		set.setDate(dto.getDate());
		set.setPassword(dto.getPassword());
		set.setStatus(dto.getStatus());
		set.getUsers().clear();
		set.getUsers().addAll(dto.getUsers() != null ? dto.getUsers()
				.stream()
				.map(u -> userMapper.toEntity(u, false, false))
				.toList() : new ArrayList());
		set.getCocktails().clear();
		set.getCocktails().addAll(dto.getCocktails() != null ? dto.getCocktails()
				.stream()
				.map(c -> cocktailMapper.toEntity(c, true))
				.toList() : new ArrayList<>());
		
		return set;
	}
}
