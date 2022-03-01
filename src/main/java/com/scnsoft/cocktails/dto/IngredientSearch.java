package com.scnsoft.cocktails.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IngredientSearch {
	
	private String lang;
	private String name;
	private String description;
	private Integer alc;
}
