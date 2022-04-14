package com.scnsoft.cocktails.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CocktailSearch {
	private String lang;
	private String name;
	private String description; 
	private String ingredientName; 
	private String ingredientDescription;

}
