package com.scnsoft.cocktails.dto;

import java.util.List;
import java.util.UUID;

import com.scnsoft.cocktails.entity.Tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class TagDTO {
	private UUID id;
	
	private LabelDTO label;
	
	private List<CocktailDTO> cocktails;
	
	public TagDTO(Tag tag, boolean nullCollection) {
		id = tag.getId();
		label = new LabelDTO(tag.getLabel());
		cocktails = nullCollection ? null : tag
				.getCocktails()
				.stream()
				.map(c -> new CocktailDTO(c, true, true))
				.toList();
	}
	
	public TagDTO(UUID id) {
		this.id = id;
	}
}
