package com.scnsoft.cocktails.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.scnsoft.cocktails.dto.LabelDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Proxy(lazy = false)
@NoArgsConstructor
@Getter @Setter 
public class Label extends AbstractEntity {
	
	private String labelEn;
	 
	private String labelRu;
	
	public Label(LabelDTO labelDTO) {
		id = labelDTO.getId();
		labelEn = labelDTO.getLabelEn();
		labelRu = labelDTO.getLabelRu();
	}
}
