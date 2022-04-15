package com.scnsoft.cocktails.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.Set;

@Repository
public interface SetRepository extends JpaRepository<Set, UUID>, JpaSpecificationExecutor<Set> {
	@Query("select count(s) from Set s join s.users u where s.status = com.scnsoft.cocktails.entity.SetStatus.ACTIVE and u.id = :userId")
	int countByUserIdWhereActive(UUID userId);
	
	@Modifying
	@Query("update Set s set s.status = com.scnsoft.cocktails.entity.SetStatus.CLOSED where FUNCTION('DATEDIFF', CURRENT_DATE, s.date) > 2")
	void closeWhereAgeMoreThanTwoDays();
}
