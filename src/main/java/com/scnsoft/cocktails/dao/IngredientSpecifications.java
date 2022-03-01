package com.scnsoft.cocktails.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.scnsoft.cocktails.dto.IngredientSearch;
import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.Ingredient;

public class IngredientSpecifications {

	public static Specification<Ingredient> empty() {
	    return new Specification<Ingredient>() {
	      public Predicate toPredicate(Root<Ingredient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.conjunction();
	      }
	    };
	  }
	
	public static Specification<Ingredient> ingredientNameStartsWith(IngredientSearch search) {
	    return new Specification<Ingredient>() {
	      public Predicate toPredicate(Root<Ingredient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.like(root.get(Ingredient.Fields.labelName).get("label" + search.getLang()), search.getName() + "%");
	      }
	    };
	  }
	
	public static Specification<Ingredient> ingredientDescriptionContains(IngredientSearch search) {
	    return new Specification<Ingredient>() {
	      public Predicate toPredicate(Root<Ingredient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.like(root.get(Ingredient.Fields.labelDescription).get("label" + search.getLang()), "%" + search.getDescription() + "%");
	      }
	    };
	  }
	
	public static Specification<Ingredient> ingredientAlcEquals(IngredientSearch search) {
	    return new Specification<Ingredient>() {
	      public Predicate toPredicate(Root<Ingredient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.equal(root.get(Ingredient.Fields.alc), search.getAlc());
	      }
	    };
	  }
}
