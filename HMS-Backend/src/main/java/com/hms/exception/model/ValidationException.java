package com.hms.exception.model;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -8374144754135909325L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}

}
