package com.scnsoft.cocktails.facade;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.scnsoft.cocktails.dto.CreateOrderDto;
import com.scnsoft.cocktails.dto.OrderDTO;
import com.scnsoft.cocktails.entity.Order;
import com.scnsoft.cocktails.mapper.OrderMapper;
import com.scnsoft.cocktails.service.OrderService;

@Component
@Transactional
public class OrderFacade {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;

	public Page<OrderDTO> findAll(HttpSession session, UUID setId, Pageable pageable) {
		
		Page<Order> page = orderService
				.findAll(session, setId, pageable);
		
		return new PageImpl<>(page
				.stream()
				.map(o -> new OrderDTO(o))
				.toList(),
				pageable, 
				page.getTotalElements());
	}

	public OrderDTO findById(HttpSession session, UUID orderId) {
		return new OrderDTO(orderService.findById(session, orderId));
	}

	public OrderDTO save(HttpSession session, CreateOrderDto createOrderDto) {
		return new OrderDTO(orderService.save(session, orderMapper.toEntity(session, createOrderDto)));
	}

	public OrderDTO update(HttpSession session, UUID orderId, OrderDTO orderDto) {
		
		orderDto.setId(orderId);
		
		return new OrderDTO(orderService.update(session, orderMapper.toEntity(orderDto)));
	}
}
