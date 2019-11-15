package com.gabrielbatista.userregapi.services.exceptions;

public class FieldNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FieldNotFoundException (String msg) {
		super(msg);
	}
	
	public FieldNotFoundException (String msg, Throwable cause) {
		super(msg, cause);
	}
}
