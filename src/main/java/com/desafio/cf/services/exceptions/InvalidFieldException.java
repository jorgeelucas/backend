package com.desafio.cf.services.exceptions;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidFieldException(String msg) {
		super(msg);
	}
	
	public InvalidFieldException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
