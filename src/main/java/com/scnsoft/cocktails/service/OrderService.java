package com.scnsoft.cocktails.service;

import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scnsoft.cocktails.dao.OrderRepository;
import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.Order;
import com.scnsoft.cocktails.entity.OrderStatus;
import com.scnsoft.cocktails.entity.SetStatus;
import com.scnsoft.cocktails.entity.User;
import com.scnsoft.cocktails.entity.UserRole;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Page<Order> findAll(HttpSession session, UUID setId, Pageable pageable) {
		
		UUID userId = (UUID)session.getAttribute("userId");
		UserRole role = (UserRole)session.getAttribute("userRole");
				
		Sort sortByTime = Sort.by(Sort.Direction.DESC, "time");
		if (Arrays.asList(UserRole.ADMIN, UserRole.BARMEN).contains(role))
			return orderRepository.findBySetId(setId, ((PageRequest)pageable).withSort(sortByTime));
		else
			return orderRepository.findBySetIdAndUserId(setId, userId, pageable);
	}

	public Order findById(HttpSession session, UUID orderId) {
		
		Order order = orderRepository.getById(orderId);
		UUID userId = (UUID)session.getAttribute("userId");
		UserRole role = (UserRole)session.getAttribute("userRole");
		
		if (role == UserRole.BARMEN && !userId.equals(order.getSet().getOwner().getId())) {
			throw new UserRoleException("Barmen can only get orders from his own sets");
		}
		if (role == UserRole.USER && !userId.equals(order.getUser().getId())) {
			throw new UserRoleException("User can only get his own orders");
		}
		
		return order;
	}

	public Order save(HttpSession session, Order order) {
		
		UUID userId = (UUID)session.getAttribute("userId");
		UserRole role = (UserRole)session.getAttribute("userRole");
		Hibernate.initialize(order.getSet().getUsers());
		order.getSet().getUsers().stream().forEach(u -> Hibernate.initialize(u));
		
		if (role != UserRole.USER)
			throw new UserRoleException("Only users can create orders");
		if (order.getSet().getStatus() != SetStatus.ACTIVE)
			throw new StatusException("Orders can be created only in active sets");
		if (!order.getSet().getUsers().contains(new User(userId)))
			throw new AuthorizationException("The user must be included in the set");
		if (!order.getSet().getCocktails().contains(new Cocktail(order.getCocktail().getId())))
			throw new AuthorizationException("The cocktail must be included in the set");
		if (orderRepository.countByUserIdAndSetIdAndStatusIn(userId, order.getSet().getId(), OrderStatus.CREATED, OrderStatus.PROCESSING) > 0)
			throw new AuthorizationException("The set must have only one active order per user");
		
		return orderRepository.save(order);
	}

	public Order update(HttpSession session, Order order) {
		
		UUID userId = (UUID)session.getAttribute("userId");
		UserRole role = (UserRole)session.getAttribute("userRole");
		
		if (!Arrays.asList(OrderStatus.CREATED, OrderStatus.PROCESSING).contains(order.getStatus()))
			throw new StatusException("Only active orders can be updated");
		if (role == UserRole.BARMEN && !userId.equals(order.getSet().getOwner().getId()))
			throw new AuthorizationException("Barmen can update only orders in his own sets");
		if (role == UserRole.USER && !userId.equals(order.getUser().getId()))
			throw new AuthorizationException("User can update only his own orders");
		
		return orderRepository.save(order);
	}
}
