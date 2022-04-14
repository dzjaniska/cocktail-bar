package com.scnsoft.cocktails.dto;

import java.time.LocalDate;

import com.scnsoft.cocktails.entity.SetStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SetSearch {
	private LocalDate date;
	private SetStatus status;
}
