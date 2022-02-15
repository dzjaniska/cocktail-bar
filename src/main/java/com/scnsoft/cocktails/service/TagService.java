package com.scnsoft.cocktails.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.TagRepository;
import com.scnsoft.cocktails.entity.Tag;

@Service
public class TagService {
	
	@Autowired
	private TagRepository tagRepository;

	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	public Tag getById(UUID tagId) {
		return tagRepository.getById(tagId);
	}

	public Tag save(Tag theTag) {
		return tagRepository.save(theTag);
	}

	public void deleteById(UUID tagId) {
		tagRepository.deleteById(tagId);
	}

	public void update(Tag theTag) {
		tagRepository.update(theTag.getId(), theTag.getLabel());		
	}

}
