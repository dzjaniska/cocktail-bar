package com.scnsoft.cocktails.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
	
	@OneToOne
	@JoinColumn(name = "name_id")
	private Label labelName;
	
	@OneToOne
	@JoinColumn(name = "description_id")
	private Label labelDescription;
	
	@OneToMany(mappedBy = "cocktail", fetch = FetchType.LAZY)
	private List<CocktailIngredient> cocktailIngredients = new ArrayList<>();
	
	public Cocktail(CocktailDTO cocktailDTO) {
		id = cocktailDTO.getId();
		image = cocktailDTO.getImage();
		labelName = new Label(cocktailDTO.getLabelDTOName());
		labelDescription = new Label(cocktailDTO.getLabelDTODescription());
		cocktailIngredients = cocktailDTO
				.getCocktailIngredientsDTO()
				.stream()
				.map(ci -> new CocktailIngredient(ci))
				.toList();
	}
}
