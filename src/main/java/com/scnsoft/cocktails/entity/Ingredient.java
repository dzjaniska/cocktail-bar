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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "name_id")
	private Label labelName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "description_id")
	private Label labelDescription;
	
	@OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
	private List<CocktailIngredient> ingredientCocktails;

	public Ingredient(IngredientDTO ingredientDTO) {
		id = ingredientDTO.getId();
		alc = ingredientDTO.getAlc();
		unit = ingredientDTO.getUnit();
		labelName = new Label(ingredientDTO.getName());
		labelDescription = new Label(ingredientDTO.getDescription());
		ingredientCocktails = null;
	}
}
