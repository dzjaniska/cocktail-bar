package com.scnsoft.cocktails.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.scnsoft.cocktails.dto.CocktailDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Cascade;

@Entity
@NoArgsConstructor
@Getter 
@Setter
@FieldNameConstants
public class Cocktail extends AbstractEntity {
	
	private String image;

	//rename into "label"
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "name_id")
	private Label labelName;

	//rename into "description"
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "description_id")
	private Label labelDescription;

	@OneToMany(mappedBy = "cocktail", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CocktailIngredient> cocktailIngredients = new ArrayList<>();
	
	public Cocktail(CocktailDTO cocktailDTO, boolean nullCollection) {
		id = cocktailDTO.getId();
		image = cocktailDTO.getImage();
		labelName = new Label(cocktailDTO.getName());
		labelDescription = new Label(cocktailDTO.getDescription());
		cocktailIngredients = nullCollection ? null : cocktailDTO
				.getCocktailIngredients()
				.stream()
				.map(ci -> new CocktailIngredient(ci, true))
				.toList();
	}
}
