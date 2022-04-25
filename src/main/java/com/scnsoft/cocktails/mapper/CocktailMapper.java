package com.scnsoft.cocktails.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.dao.CocktailRepository;
import com.scnsoft.cocktails.dto.CocktailDTO;
import com.scnsoft.cocktails.entity.Cocktail;


@Component
public class CocktailMapper {

	@Autowired
    private CocktailIngredientMapper cocktailIngredientMapper;
	
	@Autowired
    private CocktailRepository cocktailRepository;
	
	@Autowired
    private LabelMapper labelMapper;
	
	@Autowired
    private TagMapper tagMapper;

    public Cocktail toEntity(CocktailDTO dto, boolean nullCollection, boolean nullTags) {
        if(dto == null) {
            return null;
        }
        Cocktail cocktail;
        if(dto.getId() == null) {
            cocktail = new Cocktail();
        } else {
            cocktail = cocktailRepository.getById(dto.getId());
        }
        cocktail.setImage(dto.getImage());
        cocktail.setName(labelMapper.toEntity(dto.getName()));
        cocktail.setDescription(labelMapper.toEntity(dto.getDescription()));
        if (!nullCollection) {
        	 cocktail.getCocktailIngredients().clear();
             cocktail.getCocktailIngredients().addAll(
                     dto.getCocktailIngredients() != null ? dto.getCocktailIngredients().stream()
                             .map(ci -> cocktailIngredientMapper.toEntity(ci, true))
                             .peek(ci -> ci.setCocktail(cocktail))
                             .toList() : new ArrayList<>());
        }
        if (!nullTags) {
        	cocktail.getTags().clear();
        	cocktail.getTags().addAll(
        			dto.getTags() != null ? dto.getTags().stream()
        					.map(t -> tagMapper.toEntity(t, false))
        					.peek(t -> {if (!t.getCocktails().contains(cocktail))
        						t.getCocktails().add(cocktail);
        						})
        					.toList() : new ArrayList<>());
        }
       
        return cocktail;
    }
}
