package com.scnsoft.cocktails.facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.dto.TagDTO;
import com.scnsoft.cocktails.entity.Tag;
import com.scnsoft.cocktails.mapper.TagMapper;
import com.scnsoft.cocktails.service.TagService;

@Component
@Transactional
public class TagFacade {
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private TagMapper tagMapper;

	public List<TagDTO> findAll() {
		return tagService
				.findAll()
				.stream()
				.map(l -> new TagDTO(l, false))
				.toList();
	}

	
	public TagDTO getById(UUID tagId) {
		return new TagDTO(tagService.getById(tagId), false);
	}

	
	public TagDTO save(TagDTO theTag) {
		return new TagDTO(tagService.save(tagMapper.toEntity(theTag, false)), false);
	}

	
	public TagDTO update(TagDTO theTag) {
		return new TagDTO(tagService.update(tagMapper.toEntity(theTag, false)), false);
	}
	
	public void deleteById(UUID tagId) {
		tagService.delete(tagMapper.toEntity(new TagDTO(tagId), true));
	}

}
