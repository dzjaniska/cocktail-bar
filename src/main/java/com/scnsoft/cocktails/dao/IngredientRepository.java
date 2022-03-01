package com.scnsoft.cocktails.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID>, JpaSpecificationExecutor<Ingredient> {
}
