package com.scnsoft.cocktails.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "\"set\"")
@NoArgsConstructor
@Getter 
@Setter
@FieldNameConstants
public class Set extends AbstractEntity {
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "owner_id")
	private User owner;
	
	private LocalDate date;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private SetStatus status;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "set_user", joinColumns = @JoinColumn(name = "set_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "cocktail_set", joinColumns = @JoinColumn(name = "set_id"), inverseJoinColumns = @JoinColumn(name = "cocktail_id"))
	private List<Cocktail> cocktails;
}
