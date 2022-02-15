package com.scnsoft.cocktails.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class TagDTO {
	private UUID id;
	
	private LabelDTO label;
	
	public TagDTO(Tag tag) {
		id = tag.getId();
		label = new LabelDTO(tag.getLabel());
	}
}
