package com.scnsoft.cocktails.rest;

public class InvalidLangException extends RuntimeException {
	
	private final static String messagePrefix = "Invalid language selected: ";

	public InvalidLangException() {
	}

	public InvalidLangException(String lang) {
		super(messagePrefix + lang);
	}

	public InvalidLangException(Throwable cause) {
		super(cause);
	}

	public InvalidLangException(String lang, Throwable cause) {
		super(messagePrefix + lang, cause);
	}

	public InvalidLangException(String lang, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(messagePrefix + lang, cause, enableSuppression, writableStackTrace);
	}

}
