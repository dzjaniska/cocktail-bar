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
	
	public Page<SetDTO> findAll(HttpSession session, SetSearch search, Pageable pageable) {
		
		Page<Set> page = setService
				.findAll(session, search, pageable);
		
		return new PageImpl<>(page
				.stream()
				.map(s -> new SetDTO(s))
				.toList(),
				pageable, 
				page.getTotalElements());
	}

	public SetDTO findById(HttpSession session, UUID setId) {
		return new SetDTO(setService.findById(session, setId));
	}

	public SetDTO save(HttpSession session, CreateSetDTO theSet) {
		return new SetDTO(setService.save(session, setMapper.toEntity(theSet)));
	}

	public SetDTO join(HttpSession session, UUID setId, String password) {
		return new SetDTO(setService.join(session, setId, password));
	}

	public SetDTO update(HttpSession session, UUID setId, SetDTO theSet) {
		
		theSet.setId(setId);
		
		return new SetDTO(setService.update(session, setMapper.toEntity(theSet, true)));
	}

	public void delete(HttpSession session, UUID setId) {
		setService.delete(session, setId);
	}

	public SetDTO leave(HttpSession session, UUID setId, String password) {
		return new SetDTO(setService.leave(session, setId, password));
	}

}
