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
		return labelRepository.save(theLabel);
	}

	public void deleteById(UUID labelId) {
		labelRepository.deleteById(labelId);
	}
}
