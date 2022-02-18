package com.scnsoft.cocktails.facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.entity.Label;
import com.scnsoft.cocktails.entity.LabelDTO;
import com.scnsoft.cocktails.service.LabelService;

@Component
@Transactional
public class LabelFacade {
	
	@Autowired
	private LabelService labelService;

	
	public List<LabelDTO> findAll() {
		return labelService
				.findAll()
				.stream()
				.map(l -> new LabelDTO(l))
				.toList();
	}

	
	public LabelDTO getById(UUID labelId) {
		return new LabelDTO(labelService.getById(labelId));
	}

	
	public LabelDTO save(LabelDTO theLabel) {
		return new LabelDTO(labelService.save(new Label(theLabel)));
	}

	
	public LabelDTO update(LabelDTO theLabel) {
		return new LabelDTO(labelService.update(new Label(theLabel)));
	}
	
	
	public void deleteById(UUID labelId) {
		labelService.deleteById(labelId);
	}


	public boolean existsById(UUID id) {
		return labelService.existsById(id);
	}
}