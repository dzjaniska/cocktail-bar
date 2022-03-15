package com.scnsoft.cocktails.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
	ADMIN,
	BARMEN,
	USER;

	@Override
	public String getAuthority() {
		return toString();
	}
}
