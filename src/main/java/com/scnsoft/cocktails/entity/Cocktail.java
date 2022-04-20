package com.scnsoft.cocktails.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "name_id")
	private Label name;

	//rename into "description"
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "description_id")
	private Label description;

	@OneToMany(mappedBy = "cocktail", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CocktailIngredient> cocktailIngredients = new ArrayList<>();
	
	public Cocktail(CocktailDTO cocktailDTO, boolean nullCollection) {
		id = cocktailDTO.getId();
		image = cocktailDTO.getImage();
		name = new Label(cocktailDTO.getName());
		description = new Label(cocktailDTO.getDescription());
		cocktailIngredients = nullCollection ? null : cocktailDTO
				.getCocktailIngredients()
				.stream()
				.map(ci -> new CocktailIngredient(ci, true))
				.toList();
	}

	public Cocktail(UUID id) {
		this.id = id;
	}
}
