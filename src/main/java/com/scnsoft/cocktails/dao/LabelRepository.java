package com.scnsoft.cocktails.dao;

import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.Label;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LabelRepository extends JpaRepository<Label, UUID> {
}
