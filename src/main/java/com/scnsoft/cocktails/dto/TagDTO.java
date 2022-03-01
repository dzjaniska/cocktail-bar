package com.scnsoft.cocktails.dto;

import java.util.UUID;

import com.scnsoft.cocktails.entity.Tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class TagDTO {
	private UUID id;
	
	private LabelDTO labelDTO;
	
	public TagDTO(Tag tag) {
		id = tag.getId();
		labelDTO = new LabelDTO(tag.getLabel());
	}
}