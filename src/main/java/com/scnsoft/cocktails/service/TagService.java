package com.scnsoft.cocktails.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.LabelRepository;
import com.scnsoft.cocktails.dao.TagRepository;
import com.scnsoft.cocktails.entity.Label;
import com.scnsoft.cocktails.entity.Tag;

@Service
public class TagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private LabelService labelService;

	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	public Tag getById(UUID tagId) {
		return tagRepository.getById(tagId);
	}

	public Tag save(Tag theTag) {
		theTag.setId(null);
		
		Label theLabel = theTag.getLabel();
		theTag.setLabel(labelService.saveOrUpdate(theLabel));
		
//		if (!labelRepository.existsById(theLabel.getId()))
//			theLabel.setId(null);
//		else {
//			labelRepository.update(theLabel.getId(), theLabel.getLabelEn(), theLabel.getLabelRu());
//			theTag.setLabel(labelRepository.getById(theLabel.getId()));
//		}
		
		return tagRepository.save(theTag);
	}

	public Tag update(Tag theTag) {
		tagRepository.getById(theTag.getId());
		
		Label theLabel = theTag.getLabel();
		theTag.setLabel(labelService.saveOrUpdate(theLabel));
		
//		Label theLabel = theTag.getLabel();
//		if (!labelRepository.existsById(theLabel.getId()))
//		{
//			theLabel.setId(null);
//			theTag.setLabel(labelRepository.save(theLabel));
//		}
//		else
//			labelRepository.update(theLabel.getId(), theLabel.getLabelEn(), theLabel.getLabelRu());
//		tagRepository.update(theTag.getId(), theTag.getLabel());
		
		return tagRepository.save(theTag);
	}

	public void delete(Tag tag) {
		tag.getCocktails()
		.stream()
		.forEach(c -> c.getTags().remove(tag));
		tag.getCocktails().clear();
		
		tagRepository.delete(tag);
	}
}
