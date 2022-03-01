package com.scnsoft.cocktails.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.Hibernate;

import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.CocktailIngredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CocktailDTO {
	private UUID id;
	
	private String image;
	
	private LabelDTO labelDTOName;

	private LabelDTO labelDTODescription;

	private List<CocktailIngredientDTO> cocktailIngredientsDTO;
	
	public CocktailDTO(Cocktail cocktail, boolean nullCollection) {
		id = cocktail.getId();
		image = cocktail.getImage();
		labelDTOName = new LabelDTO(cocktail.getLabelName());
		labelDTODescription = new LabelDTO(cocktail.getLabelDescription());
//		List<CocktailIngredient> ingredients = cocktail.getCocktailIngredients();
//		Hibernate.initialize(ingredients);
//		System.out.println(Hibernate.isInitialized(ingredients));
		cocktailIngredientsDTO = nullCollection ? null : cocktail
				.getCocktailIngredients()
				.stream()
				.map(ci -> new CocktailIngredientDTO(ci, true))
				.toList();
	}
}
