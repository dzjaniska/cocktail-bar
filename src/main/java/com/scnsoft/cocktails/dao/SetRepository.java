package com.scnsoft.cocktails.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.Set;

@Repository
public interface SetRepository extends JpaRepository<Set, UUID>, JpaSpecificationExecutor<Set> {
}
