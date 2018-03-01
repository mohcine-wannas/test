package com.ayouris.tawassol.rest.exception;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6226729777440224408L;
	private String[] params;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, String[] params) {
		super(message);
		this.params = params;
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

}