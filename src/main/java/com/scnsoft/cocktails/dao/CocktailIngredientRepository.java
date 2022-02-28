package com.scnsoft.cocktails.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.CocktailIngredient;

@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, UUID> {
}
