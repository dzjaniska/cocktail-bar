package com.scnsoft.cocktails.facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.entity.Tag;
import com.scnsoft.cocktails.entity.TagDTO;
import com.scnsoft.cocktails.service.TagService;

@Component
public class TagFacade {
	
	@Autowired
	private TagService tagService;

	@Transactional
	public List<TagDTO> findAll() {
		return tagService
				.findAll()
				.stream()
				.map(l -> new TagDTO(l))
				.toList();
	}

	@Transactional
	public TagDTO getById(UUID tagId) {
		return new TagDTO(tagService.getById(tagId));
	}

	@Transactional
	public TagDTO save(TagDTO theTag) {
		return new TagDTO(tagService.save(new Tag(theTag)));
	}

	@Transactional
	public void deleteById(UUID tagId) {
		tagService.deleteById(tagId);
	}

	@Transactional
	public void update(TagDTO theTag) {
		tagService.update(new Tag(theTag));
	}

}
