package com.scnsoft.cocktails.mapper;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scnsoft.cocktails.JwtTokenUtil;
import com.scnsoft.cocktails.dao.CocktailRepository;
import com.scnsoft.cocktails.dao.OrderRepository;
import com.scnsoft.cocktails.dao.SetRepository;
import com.scnsoft.cocktails.dao.UserRepository;
import com.scnsoft.cocktails.dto.CreateOrderDto;
import com.scnsoft.cocktails.dto.OrderDTO;
import com.scnsoft.cocktails.entity.Order;
import com.scnsoft.cocktails.entity.OrderStatus;

@Component
public class OrderMapper {
	
	@Autowired
	private SetRepository setRepository;
	
	@Autowired
	private CocktailRepository cocktailRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public Order toEntity(CreateOrderDto dto) {
		
		if(dto == null) {
            return null;
        }
		
		Order order = new Order();
		order.setStatus(OrderStatus.CREATED);
		order.setTime(LocalTime.now());
		order.setSet(setRepository.getById(dto.getSetId()));
		order.setCocktail(cocktailRepository.getById(dto.getCocktailId()));
		order.setUser(userRepository.getById(jwtTokenUtil.getUserId()));
		
		return order;
	}

	public Order toEntity(OrderDTO dto) {
		
		if(dto == null) {
            return null;
        }
		
		Order order = orderRepository.getById(dto.getId());
		order.setStatus(dto.getStatus());
		order.setTime(dto.getTime());
		order.setSet(setRepository.getById(dto.getSet().getId()));
		order.setCocktail(cocktailRepository.getById(dto.getCocktail().getId()));
		order.setUser(userRepository.getById(dto.getUser().getId()));
		
		return order;
	}

}
