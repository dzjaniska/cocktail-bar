package com.scnsoft.cocktails.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.Cocktail;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, UUID>, JpaSpecificationExecutor<Cocktail> {
}
