package com.scnsoft.cocktails.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.Label;
import com.scnsoft.cocktails.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
	
//	@Modifying
//	@Query("update Tag t set t.label = :label where t.id = :id")
//	public int update(@Param("id") UUID id, @Param("label") Label label);
}
