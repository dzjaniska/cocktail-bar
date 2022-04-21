package com.scnsoft.cocktails.dao;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scnsoft.cocktails.entity.Order;
import com.scnsoft.cocktails.entity.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
	
	Page<Order> findBySetId(UUID setId, Pageable pageable);
	
	Page<Order> findBySetIdAndUserId(UUID setId, UUID userId, Pageable pageable);
	
	int countByUserIdAndSetIdAndStatusIn(UUID userId, UUID setId, OrderStatus... statuses);
}
