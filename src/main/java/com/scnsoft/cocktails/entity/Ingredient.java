package com.scnsoft.cocktails.entity;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter 
public class Ingredient {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "uuid2",
        parameters = {
            @Parameter(
                name = "uuid_gen_strategy_class",
                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
        }
    )
	private UUID id;
	
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
}
