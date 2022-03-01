package com.scnsoft.cocktails.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.scnsoft.cocktails.dto.CocktailDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@NoArgsConstructor
@Getter 
@Setter
@FieldNameConstants
public class Cocktail extends AbstractEntity {
	
	private String image;

	//rename into "label"
	@OneToOne
	@JoinColumn(name = "name_id")
	private Label labelName;

	//rename into "description"
	@OneToOne
	@JoinColumn(name = "description_id")
	private Label labelDescription;
	
	@OneToMany(mappedBy = "cocktail", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CocktailIngredient> cocktailIngredients = new ArrayList<>();
	
	public Cocktail(CocktailDTO cocktailDTO) {
		id = cocktailDTO.getId();
		image = cocktailDTO.getImage();
		labelName = new Label(cocktailDTO.getName());
		labelDescription = new Label(cocktailDTO.getDescription());
		cocktailIngredients = cocktailDTO
				.getCocktailIngredients()
				.stream()
				.map(ci -> new CocktailIngredient(ci))
				.toList();
	}
}
