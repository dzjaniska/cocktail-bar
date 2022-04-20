package com.scnsoft.cocktails.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrderDto {
	
	private UUID cocktailId;
	private UUID setId;
}
