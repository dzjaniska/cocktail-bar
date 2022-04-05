package com.scnsoft.cocktails.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateSetDTO {
	
	LocalDate date;
	
	String password; 
}
