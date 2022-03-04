package com.scnsoft.cocktails.facade;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.dto.CocktailDTO;
import com.scnsoft.cocktails.dto.CocktailSearch;
import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.service.CocktailService;

@Transactional
@Component
public class CocktailFacade {
	
	@Autowired
	CocktailService cocktailService;
	
	public Page<CocktailDTO> findAll(CocktailSearch search, Pageable pageable) {
		
		return new PageImpl<>(cocktailService
				.findAll(search, pageable)
				.stream()
				.map(c -> new CocktailDTO(c, false))
				.toList());
	}
	
	public CocktailDTO findById(UUID id) {
		return new CocktailDTO(cocktailService.findById(id), false);
	}

	public CocktailDTO save(CocktailDTO theCocktail) {
		return new CocktailDTO(cocktailService.save(new Cocktail(theCocktail, false), false), false);
	}
	
	public CocktailDTO update(CocktailDTO theCocktail) {
		return new CocktailDTO(cocktailService.update(new Cocktail(theCocktail, false), false), false);
	}
	
	public void deleteById(UUID id) {
		cocktailService.deleteById(id);
	}
}
