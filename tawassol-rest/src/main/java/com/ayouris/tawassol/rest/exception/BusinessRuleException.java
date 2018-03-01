package com.ayouris.tawassol.rest.exception;


public class BusinessRuleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5633454096491224421L;
	
	private String[] params;

	public BusinessRuleException(String message) {
		super(message);
	}

	public BusinessRuleException(String message, String[] params) {
		super(message);
		this.params = params;
	}

	public BusinessRuleException(Throwable cause) {
		super(cause);
	}

	public BusinessRuleException(String message, Throwable cause) {
		super(message, cause);
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

}