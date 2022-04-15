package com.scnsoft.cocktails.entity;

import java.util.List;

import javax.persistence.*;

import com.scnsoft.cocktails.dto.IngredientDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@NoArgsConstructor
@Getter 
@Setter
@FieldNameConstants
public class Ingredient extends AbstractEntity {
	
	private int alc;
	
	private String unit;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "name_id")
	private Label name;

	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "description_id")
	private Label description;
	
	@OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CocktailIngredient> ingredientCocktails;

	public Ingredient(IngredientDTO ingredientDTO, boolean nullCollection) {
		id = ingredientDTO.getId();
		alc = ingredientDTO.getAlc();
		unit = ingredientDTO.getUnit();
		name = new Label(ingredientDTO.getName());
		description = new Label(ingredientDTO.getDescription());
		ingredientCocktails = nullCollection ? null : ingredientDTO
				.getIngredientCocktails()
				.stream()
				.map(ci -> new CocktailIngredient(ci, false))
				.toList();
	}
}
