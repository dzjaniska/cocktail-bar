package com.scnsoft.cocktails.facade;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.dto.IngredientDTO;
import com.scnsoft.cocktails.dto.IngredientSearch;
import com.scnsoft.cocktails.entity.Ingredient;
import com.scnsoft.cocktails.mapper.IngredientMapper;
import com.scnsoft.cocktails.service.IngredientService;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class IngredientFacade {
	
	private final IngredientService ingredientService;
	
	private final IngredientMapper ingredientMapper;
	
	public Page<IngredientDTO> findAll(IngredientSearch search, Pageable pageable) {
		
		Page<Ingredient> page = ingredientService
				.findAll(search, pageable);
		
		return new PageImpl<>(page
				.stream()
				.map(c -> new IngredientDTO(c, false))
				.toList(), 
				pageable, 
				page.getTotalElements());
	}

	public IngredientDTO findById(UUID ingredientId) {
		return new IngredientDTO(ingredientService.findById(ingredientId), false);
	}

	public IngredientDTO save(IngredientDTO theIngredient) {
		theIngredient.setId(null);
		
		return new IngredientDTO(ingredientService.save(ingredientMapper.toEntity(theIngredient, false), false), false);
	}

	public IngredientDTO update(IngredientDTO theIngredient) {
		return new IngredientDTO(ingredientService.update(ingredientMapper.toEntity(theIngredient, false), false), false);
	}

	public void deleteById(UUID ingredientId) {
		ingredientService.deleteById(ingredientId);		
	}
}
