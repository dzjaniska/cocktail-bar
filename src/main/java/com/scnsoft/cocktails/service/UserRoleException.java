package com.scnsoft.cocktails.service;

public class UserRoleException extends RuntimeException {

	public UserRoleException() {
	}

	public UserRoleException(String message) {
		super(message);
	}

	public UserRoleException(Throwable cause) {
		super(cause);
	}

	public UserRoleException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
