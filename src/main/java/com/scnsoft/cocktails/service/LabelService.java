package com.scnsoft.cocktails.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.dao.LabelRepository;
import com.scnsoft.cocktails.entity.Label;

@Service
public class LabelService{
	
	@Autowired
	private LabelRepository labelRepository;

	public List<Label> findAll() {
		return labelRepository.findAll();
	}

	public Label getById(UUID labelId) {
		return labelRepository.getById(labelId);
	}

	public Label save(Label theLabel) {
		theLabel.setId(null);
		
		return labelRepository.save(theLabel);
	}

	public Label update(Label theLabel) {
		labelRepository.getById(theLabel.getId());
		
		return labelRepository.save(theLabel);
	}
	
	public Label saveOrUpdate(Label theLabel) {
		if (theLabel.getId() == null)
			return save(theLabel);
		else
			return update(theLabel);
	}
	
	public void deleteById(UUID labelId) {
		labelRepository.deleteById(labelId);
	}

	public boolean existsById(UUID id) {
		return labelRepository.existsById(id);
	}
}
