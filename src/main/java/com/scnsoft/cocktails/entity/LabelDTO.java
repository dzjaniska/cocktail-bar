package com.scnsoft.cocktails.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class LabelDTO {
	private UUID id;
	
	private String labelEn;
	 
	private String labelRu;
	
	public LabelDTO(Label label) {
		id = label.getId();
		labelEn = label.getLabelEn();
		labelRu = label.getLabelRu();
	}
}
