package com.scnsoft.cocktails.mapper;

import com.scnsoft.cocktails.dao.CocktailIngredientRepository;
import com.scnsoft.cocktails.dto.CocktailIngredientDTO;
import com.scnsoft.cocktails.entity.CocktailIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CocktailIngredientMapper {

    private final CocktailIngredientRepository cocktailIngredientRepository;
    private final IngredientMapper ingredientMapper;

    public CocktailIngredient toEntity(CocktailIngredientDTO dto) {
        if(dto == null) {
            return null;
        }
        CocktailIngredient out;
        if(dto.getId() == null) {
            out = new CocktailIngredient();
        } else {
            out = cocktailIngredientRepository.getById(dto.getId());
        }
        out.setIngredient(ingredientMapper.toEntity(dto.getIngredient()));
        out.setQuantity(dto.getQuantity());
        return out;
    }
}
