package com.scnsoft.cocktails.dto;

import java.util.List;
import java.util.UUID;

import com.scnsoft.cocktails.entity.Cocktail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CocktailDTO {
	private UUID id;
	
	private String image;
	
	private LabelDTO name;

	private LabelDTO description;

	private List<CocktailIngredientDTO> cocktailIngredients;
	
	public CocktailDTO(Cocktail cocktail, boolean nullCollection) {
		id = cocktail.getId();
		image = cocktail.getImage();
		name = new LabelDTO(cocktail.getLabelName());
		description = new LabelDTO(cocktail.getLabelDescription());
//		List<CocktailIngredient> ingredients = cocktail.getCocktailIngredients();
//		Hibernate.initialize(ingredients);
//		System.out.println(Hibernate.isInitialized(ingredients));
		cocktailIngredients = nullCollection ? null : cocktail
				.getCocktailIngredients()
				.stream()
				.map(ci -> new CocktailIngredientDTO(ci, true))
				.toList();
	}
}
