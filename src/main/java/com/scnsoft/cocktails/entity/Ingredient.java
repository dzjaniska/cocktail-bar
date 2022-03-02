package com.scnsoft.cocktails.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

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
	
	@OneToOne
	@JoinColumn(name = "name_id")
	private Label labelName;
	
	@OneToOne
	@JoinColumn(name = "description_id")
	private Label labelDescription;
	
	@OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
	private List<CocktailIngredient> ingredientCocktails;

	public Ingredient(IngredientDTO ingredientDTO, boolean nullCollection) {
		id = ingredientDTO.getId();
		alc = ingredientDTO.getAlc();
		unit = ingredientDTO.getUnit();
		labelName = new Label(ingredientDTO.getLabelDTOName());
		labelDescription = new Label(ingredientDTO.getLabelDTODescription());
		ingredientCocktails = nullCollection ? null : ingredientDTO
				.getIngredientCocktailsDTO()
				.stream()
				.map(ci -> new CocktailIngredient(ci, false))
				.toList();
	}
}
