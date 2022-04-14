package com.scnsoft.cocktails.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@NoArgsConstructor
@Getter 
@Setter
@FieldNameConstants
public class User extends AbstractEntity implements UserDetails {
	
	@Transient
	private boolean enabled = true;
	
	private String title;
	
	private String login;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Override
	public Collection<UserRole> getAuthorities() {
		return Arrays.asList(role);
	}

	@Override
	public String getUsername() {
		return getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
