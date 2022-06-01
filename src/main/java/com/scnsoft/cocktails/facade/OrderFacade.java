package com.scnsoft.cocktails.facade;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.jose4j.lang.JoseException;
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

	public Page<OrderDTO> findAll(UUID setId, Pageable pageable) {
		
		Page<Order> page = orderService
				.findAll(setId, pageable);
		
		return new PageImpl<>(page
				.stream()
				.map(o -> new OrderDTO(o))
				.toList(),
				pageable, 
				page.getTotalElements());
	}

	public OrderDTO findById(UUID orderId) {
		return new OrderDTO(orderService.findById(orderId));
	}

	public OrderDTO save(CreateOrderDto createOrderDto) throws GeneralSecurityException, IOException, JoseException, ExecutionException, InterruptedException {
		return new OrderDTO(orderService.save(orderMapper.toEntity(createOrderDto)));
	}

	public OrderDTO update(UUID orderId, OrderDTO orderDto) {
		
		orderDto.setId(orderId);
		
		return new OrderDTO(orderService.update(orderMapper.toEntity(orderDto)));
	}
}
