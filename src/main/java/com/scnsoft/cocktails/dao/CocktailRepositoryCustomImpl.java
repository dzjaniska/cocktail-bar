package com.scnsoft.cocktails.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.CocktailIngredient;
import com.scnsoft.cocktails.entity.Ingredient;
import com.scnsoft.cocktails.entity.Label;

public class CocktailRepositoryCustomImpl implements CocktailRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Cocktail> findByNameAndDescriptionAndIngredientNameAndIngredientDescription(String lang, String name,
			String description, String ingredientName, String ingredientDescription) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cocktail> query = builder.createQuery(Cocktail.class);
		Root<Cocktail> cocktail = query.from(Cocktail.class);
//		Fetch<Cocktail, Ingredient> fetchCocktailIngredient = cocktail.fetch("cocktailIngredients").fetch("ingredient");
//		cocktail.fetch("labelName");
//		cocktail.fetch("labelDescription");
//		fetchCocktailIngredient.fetch("labelName");
//		fetchCocktailIngredient.fetch("labelDescription");
		Join<Cocktail, CocktailIngredient> cocktailIngredient = cocktail.join("cocktailIngredients");
		Join<CocktailIngredient, Ingredient> ingredient = cocktailIngredient.join("ingredient");
		Join<Ingredient, Label> labelIngredientName = ingredient.join("labelName");
		Join<Ingredient, Label> labelIngredientDescription = ingredient.join("labelDescription");
		
		List<Predicate> predicates = new ArrayList<>();
		if (name != null)
			predicates.add(builder.like(cocktail.get("labelName").get("label" + lang), name + "%"));
		if (description != null)
			predicates.add(builder.like(cocktail.get("labelDescription").get("label" + lang), "%" + description + "%"));
		if (ingredientName != null)
			predicates.add(builder.like(labelIngredientName.get("label" + lang), ingredientName + "%"));
		if (ingredientDescription != null)
			predicates.add(builder.like(labelIngredientDescription.get("label" + lang), "%" + ingredientDescription + "%"));
		query = query.where(predicates.toArray(new Predicate[0])).distinct(true);
		
		return entityManager.createQuery(query).getResultList();
	}

}
