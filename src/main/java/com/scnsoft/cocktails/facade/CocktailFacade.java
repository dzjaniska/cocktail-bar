package com.scnsoft.cocktails.facade;

import java.util.UUID;

import com.scnsoft.cocktails.mapper.CocktailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.dto.CocktailDTO;
import com.scnsoft.cocktails.dto.CocktailSearch;
import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.Ingredient;
import com.scnsoft.cocktails.service.CocktailService;

@Transactional
@Component
@RequiredArgsConstructor
public class CocktailFacade {

	private final CocktailService cocktailService;

	private final CocktailMapper cocktailMapper;
	
	public Page<CocktailDTO> findAll(CocktailSearch search, Pageable pageable) {
		
		Page<Cocktail> page = cocktailService
				.findAll(search, pageable);
		
		return new PageImpl<>(page
				.stream()
				.map(c -> new CocktailDTO(c, true, false))
				.toList(),
				pageable, 
				page.getTotalElements());
	}
	
	public CocktailDTO findById(UUID id) {
		return new CocktailDTO(cocktailService.findById(id), false, false);
	}

	public CocktailDTO save(CocktailDTO theCocktail) {
		theCocktail.setId(null);
		
		return new CocktailDTO(cocktailService.save(cocktailMapper.toEntity(theCocktail, false, false), false), false, false);
	}
	
	public CocktailDTO update(CocktailDTO theCocktail) {
		return new CocktailDTO(cocktailService.update(cocktailMapper.toEntity(theCocktail, false, false), false), false, false);
	}
	
	public void delete(UUID id) {
		cocktailService.delete(cocktailMapper.toEntity(new CocktailDTO(id), true, true));
	}
}
