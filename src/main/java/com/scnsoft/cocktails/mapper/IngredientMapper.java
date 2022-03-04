package com.scnsoft.cocktails.mapper;

import com.scnsoft.cocktails.dao.IngredientRepository;
import com.scnsoft.cocktails.dto.IngredientDTO;
import com.scnsoft.cocktails.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientMapper {
    private final IngredientRepository ingredientRepository;
    private final LabelMapper labelMapper;

    public Ingredient toEntity(IngredientDTO dto) {
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
        out.setLabelDescription(labelMapper.toEntity(dto.getDescription()));
        out.setLabelName(labelMapper.toEntity(dto.getName()));
        return out;
    }
}
