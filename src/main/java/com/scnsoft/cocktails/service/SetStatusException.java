package com.scnsoft.cocktails.service;

public class SetStatusException extends RuntimeException {

	public SetStatusException() {
	}

	public SetStatusException(String message) {
		super(message);
	}

	public SetStatusException(Throwable cause) {
		super(cause);
	}

	public SetStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public SetStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
