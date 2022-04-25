package com.scnsoft.cocktails.dto;

import java.time.LocalTime;
import java.util.UUID;

import com.scnsoft.cocktails.entity.Cocktail;
import com.scnsoft.cocktails.entity.Order;
import com.scnsoft.cocktails.entity.OrderStatus;
import com.scnsoft.cocktails.entity.Set;
import com.scnsoft.cocktails.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class OrderDTO {
	
	private UUID id;
	
	private OrderStatus status;
	
	private LocalTime time;
	
	private SetDTO set;
	
	private CocktailDTO cocktail;

	private UserDTO user;
	
	public OrderDTO(Order order) {
		id = order.getId();
		status = order.getStatus();
		time = order.getTime();
		set = new SetDTO(order.getSet());
		cocktail = new CocktailDTO(order.getCocktail(), false, false);
		user = new UserDTO(order.getUser());
	}
}
