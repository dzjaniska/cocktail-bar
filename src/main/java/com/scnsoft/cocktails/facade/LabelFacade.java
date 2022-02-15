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
public class LabelFacade {
	
	@Autowired
	private LabelService labelService;

	@Transactional
	public List<LabelDTO> findAll() {
		return labelService
				.findAll()
				.stream()
				.map(l -> new LabelDTO(l))
				.toList();
	}

	@Transactional
	public LabelDTO getById(UUID labelId) {
		return new LabelDTO(labelService.getById(labelId));
	}

	@Transactional
	public LabelDTO save(LabelDTO theLabel) {
		return new LabelDTO(labelService.save(new Label(theLabel)));
	}

	@Transactional
	public void deleteById(UUID labelId) {
		labelService.deleteById(labelId);
	}

	@Transactional
	public void update(LabelDTO theLabel) {
		labelService.update(new Label(theLabel));
	}

	public boolean existsById(UUID id) {
		return labelService.existsById(id);
	}
}