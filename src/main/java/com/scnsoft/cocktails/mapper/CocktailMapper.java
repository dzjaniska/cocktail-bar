package com.scnsoft.cocktails.mapper;

import com.scnsoft.cocktails.dao.CocktailRepository;
import com.scnsoft.cocktails.dto.CocktailDTO;
import com.scnsoft.cocktails.entity.Cocktail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@RequiredArgsConstructor
public class CocktailMapper {

    private final CocktailIngredientMapper cocktailIngredientMapper;
    private final CocktailRepository cocktailRepository;
    private final LabelMapper labelMapper;


    public Cocktail toEntity(CocktailDTO dto) {
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
        cocktail.setLabelName(labelMapper.toEntity(dto.getName()));
        cocktail.setLabelDescription(labelMapper.toEntity(dto.getDescription()));
        cocktail.getCocktailIngredients().clear();
        cocktail.getCocktailIngredients().addAll(
                dto.getCocktailIngredients() != null ? dto.getCocktailIngredients().stream()
                        .map(cocktailIngredientMapper::toEntity)
                        .peek(i -> i.setCocktail(cocktail))
                        .toList() : new ArrayList<>());
        return cocktail;
    }
}
