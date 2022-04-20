package com.scnsoft.cocktails.rest;

import java.util.NoSuchElementException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scnsoft.cocktails.dto.CreateOrderDto;
import com.scnsoft.cocktails.dto.OrderDTO;
import com.scnsoft.cocktails.facade.OrderFacade;
import com.scnsoft.cocktails.service.AuthorizationException;
import com.scnsoft.cocktails.service.StatusException;
import com.scnsoft.cocktails.service.UserRoleException;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
	
	@Autowired
	private OrderFacade orderFacade;

	@GetMapping("/{setId}")
	public Page<OrderDTO> getOrders(HttpSession session, @PathVariable UUID setId, Pageable pageable) {
		return orderFacade.findAll(session, setId, pageable);
	}

	@GetMapping("/single/{orderId}")
	public OrderDTO getOrderById(HttpSession session, @PathVariable UUID orderId) {
		return orderFacade.findById(session, orderId);
	}
	
	@PostMapping
	public OrderDTO createOrder(HttpSession session, @RequestBody CreateOrderDto createOrderDto) {
		return orderFacade.save(session, createOrderDto);
	}
	
	@PutMapping("/{orderId}")
	public OrderDTO updateOrder(HttpSession session, @PathVariable UUID orderId, @RequestBody OrderDTO orderDto) {
		return orderFacade.update(session, orderId, orderDto);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(NoSuchElementException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(EmptyResultDataAccessException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(UserRoleException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(StatusException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(AuthorizationException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
