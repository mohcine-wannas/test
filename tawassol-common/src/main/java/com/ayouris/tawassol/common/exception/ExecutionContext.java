package com.ayouris.tawassol.common.exception;

import lombok.Getter;

@Getter
public class ExecutionContext {
	
	private String className;
	private String methodName;
	String args = null;
	
	public ExecutionContext(String className, String methodName,  String args){
		this.className = className;
		this.methodName = methodName;
		this.args = args;
	}
	
}
