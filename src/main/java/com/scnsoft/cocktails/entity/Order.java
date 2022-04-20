package com.scnsoft.cocktails.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "\"order\"")
@NoArgsConstructor
@Getter 
@Setter
@FieldNameConstants
public class Order extends AbstractEntity {
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private LocalTime time;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "set_id")
	private Set set;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cocktail_id")
	private Cocktail cocktail;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

}
