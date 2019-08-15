package com.algo.trading.exceptions;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 2051613470041602999L;

	private String error;

	public ApplicationException(final String message) {
		super(message);
		this.error = message;
	}
}
