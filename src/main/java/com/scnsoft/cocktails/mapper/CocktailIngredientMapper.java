package com.scnsoft.cocktails.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.dao.CocktailIngredientRepository;
import com.scnsoft.cocktails.dto.CocktailIngredientDTO;
import com.scnsoft.cocktails.entity.CocktailIngredient;

@Component
public class CocktailIngredientMapper {

	@Autowired
    private CocktailIngredientRepository cocktailIngredientRepository;
	
	@Autowired
    private CocktailMapper cocktailMapper;
	
	@Autowired
    private IngredientMapper ingredientMapper;

    public CocktailIngredient toEntity(CocktailIngredientDTO dto, boolean nullCocktail) {
        if(dto == null) {
            return null;
        }
        CocktailIngredient out;
        if(dto.getId() == null) {
            out = new CocktailIngredient();
        } else {
            out = cocktailIngredientRepository.getById(dto.getId());
        }
        out.setCocktail(nullCocktail ? null : cocktailMapper.toEntity(dto.getCocktailDTO(), true, false));
        out.setIngredient(nullCocktail ? ingredientMapper.toEntity(dto.getIngredientDTO(), true) : null);
        out.setQuantity(dto.getQuantity());
        return out;
    }
}
