package com.scnsoft.cocktails.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.dao.IngredientRepository;
import com.scnsoft.cocktails.dto.IngredientDTO;
import com.scnsoft.cocktails.entity.Ingredient;

@Component
public class IngredientMapper {
	
	@Autowired
	private CocktailIngredientMapper cocktailIngredientMapper;
	
	@Autowired
    private IngredientRepository ingredientRepository;
	
	@Autowired
    private LabelMapper labelMapper;

    public Ingredient toEntity(IngredientDTO dto, boolean nullCollection) {
        if(dto == null) {
            return null;
        }
        Ingredient out;
        if(dto.getId() == null) {
            out = new Ingredient();
        } else {
            out = ingredientRepository.getById(dto.getId());
        }
        out.setAlc(dto.getAlc());
        out.setUnit(dto.getUnit());
        out.setDescription(labelMapper.toEntity(dto.getDescription()));
        out.setName(labelMapper.toEntity(dto.getName()));
        if (!nullCollection) {
        	out.getIngredientCocktails().clear();
        	out.getIngredientCocktails().addAll(
                    dto.getIngredientCocktails() != null ? dto.getIngredientCocktails().stream()
                            .map(ic -> cocktailIngredientMapper.toEntity(ic, false))
                            .peek(ic -> ic.setIngredient(out))
                            .toList() : new ArrayList<>());
        }
        
        return out;
    }
}
