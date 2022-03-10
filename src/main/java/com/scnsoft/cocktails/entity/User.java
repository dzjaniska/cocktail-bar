package com.scnsoft.cocktails.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@NoArgsConstructor
@Getter 
@Setter
@FieldNameConstants
public class User extends AbstractEntity {
	
	private String title;
	
	private String login;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;

}
