package com.scnsoft.cocktails.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.common.ExpressionUtils;

import com.scnsoft.cocktails.dto.CocktailSearch;
import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.CocktailIngredient;
import com.scnsoft.cocktails.entity.Ingredient;

import lombok.AllArgsConstructor;

public class CocktailSpecifications {
	
	public static Specification<Cocktail> empty() {
	    return new Specification<Cocktail>() {
	      public Predicate toPredicate(Root<Cocktail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.conjunction();
	      }
	    };
	  }
	
	public static Specification<Cocktail> cocktailNameStartsWith(CocktailSearch search) {
	    return new Specification<Cocktail>() {
	      public Predicate toPredicate(Root<Cocktail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.like(root.get(Cocktail.Fields.name).get("label" + search.getLang()), search.getName() + "%");
	      }
	    };
	  }
	
	public static Specification<Cocktail> cocktailDescriptionContains(CocktailSearch search) {
	    return new Specification<Cocktail>() {
	      public Predicate toPredicate(Root<Cocktail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        return cb.like(root.get(Cocktail.Fields.description).get("label" + search.getLang()), "%" + search.getDescription() + "%");
	      }
	    };
	  }

	public static Specification<Cocktail> cocktailIngredientStartsWith(CocktailSearch search) {
	    return new Specification<Cocktail>() {
	      public Predicate toPredicate(Root<Cocktail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	    	  Join<Cocktail, Ingredient> ingredient = root.join("cocktailIngredients").join("ingredient");
	        return cb.like(ingredient.get(Ingredient.Fields.name).get("label" + search.getLang()), search.getIngredientName() + "%");
	      }
	    };
	  }
	
	public static Specification<Cocktail> cocktailIngredientContains(CocktailSearch search) {
	    return new Specification<Cocktail>() {
	      public Predicate toPredicate(Root<Cocktail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	    	  Join<Cocktail, Ingredient> ingredient = root.join("cocktailIngredients").join("ingredient");
	        return cb.like(ingredient.get(Ingredient.Fields.description).get("label" + search.getLang()), "%" + search.getIngredientDescription() + "%");
	      }
	    };
	  }
}
