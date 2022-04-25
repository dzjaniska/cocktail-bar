package com.scnsoft.cocktails.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.dao.TagRepository;
import com.scnsoft.cocktails.dto.TagDTO;
import com.scnsoft.cocktails.entity.Tag;

@Component
public class TagMapper {
	
	@Autowired
    private TagRepository tagRepository;
	
	@Autowired
    private LabelMapper labelMapper;
	
	@Autowired
    private CocktailMapper cocktailMapper;
	
	public Tag toEntity(TagDTO dto, boolean nullCollection) {
		if(dto == null) {
            return null;
        }
        Tag tag;
        if(dto.getId() == null) {
            tag = new Tag();
        } else {
            tag = tagRepository.getById(dto.getId());
        }
        tag.setLabel(labelMapper.toEntity(dto.getLabel()));
        if (!nullCollection) {
        	tag.getCocktails().clear();
        	tag.getCocktails().addAll(
        			dto.getCocktails() != null ? dto
        					.getCocktails()
        					.stream()
        					.map(c -> cocktailMapper.toEntity(c, true, true))
        					.peek(c -> {if (!c.getTags().contains(tag))
        						c.getTags().add(tag);
        						})
        					.toList() : new ArrayList<>());
        }
        
        return tag;
	}

}
