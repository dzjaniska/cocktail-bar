package com.scnsoft.cocktails.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, UUID> {
	@Modifying
	@Query("update Label l set l.labelEn = :labelEn, l.labelRu = :labelRu where l.id = :id")
	public int update(@Param("id") UUID id, @Param("labelEn") String labelEn, @Param("labelRu") String labelRu);
}
