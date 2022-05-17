package com.scnsoft.cocktails.facade;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.dto.CreateSetDTO;
import com.scnsoft.cocktails.dto.SetDTO;
import com.scnsoft.cocktails.dto.SetSearch;
import com.scnsoft.cocktails.entity.Set;
import com.scnsoft.cocktails.mapper.SetMapper;
import com.scnsoft.cocktails.service.SetService;

@Component
@Transactional
public class SetFacade {
	
	@Autowired
	private SetService setService;
	
	@Autowired
	private SetMapper setMapper;
	
	public Page<SetDTO> findAll(SetSearch search, Pageable pageable) {
		
		Page<Set> page = setService
				.findAll(search, pageable);
		
		return new PageImpl<>(page
				.stream()
				.map(s -> new SetDTO(s, true))
				.toList(),
				pageable, 
				page.getTotalElements());
	}

	public SetDTO findById(UUID setId) {
		return new SetDTO(setService.findById(setId), false);
	}

	public SetDTO save(CreateSetDTO theSet) {
		return new SetDTO(setService.save(setMapper.toEntity(theSet)), false);
	}

	public SetDTO join(UUID setId, String password) {
		return new SetDTO(setService.join(setId, password), false);
	}

	public SetDTO update(UUID setId, SetDTO theSet) {
		
		theSet.setId(setId);
		
		return new SetDTO(setService.update(setMapper.toEntity(theSet, true)), false);
	}

	public void delete(UUID setId) {
		setService.delete(setId);
	}

	public SetDTO leave(UUID setId, String password) {
		return new SetDTO(setService.leave(setId, password), false);
	}

}
