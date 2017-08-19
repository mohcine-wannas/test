package com.ayouris.tawassol.common.exception;

import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@SuppressWarnings("unused")
public class BackendException extends RuntimeException {

	private static final long serialVersionUID = 6204548847348758051L;

	private ErrorCode errorCode;
	private String errorId;
	private String userMessage;
	private String debugMessage;
	private Object[] debugMessageParams;
	private int lineNumber = 0;
	private LinkedList<ExecutionContext> executionContexts = new LinkedList<>();
	
	
	public BackendException(ErrorCode errorCode,Object...debugMessageParams) {
		this.errorCode = errorCode;
		this.debugMessageParams = debugMessageParams;
	}

	public BackendException(String message, ErrorCode errorCode,Object...debugMessageParams) {
		super(message);
		this.errorCode = errorCode;
		this.debugMessageParams = debugMessageParams;
	}

	public BackendException(Exception cause, ErrorCode errorCode,Object...debugMessageParams) {
		super(cause);
		this.errorCode = errorCode;
		this.debugMessageParams = debugMessageParams;
	}

	public BackendException(String message, Exception cause, ErrorCode errorCode,Object...debugMessageParams) {
		super(message, cause);
		this.errorCode = errorCode;
		this.debugMessageParams = debugMessageParams;
	}

	public static BackendException wrap(Exception exception, ErrorCode errorCode) {
		if (exception instanceof BackendException) {
			BackendException se = (BackendException) exception;
			if (errorCode != null && errorCode != se.getErrorCode()) {
				return new BackendException(exception.getMessage(), exception, errorCode);
			}
			return se;
		} else {
			return new BackendException(exception.getMessage(), exception, errorCode);
		}
	}

	public static BackendException wrap(Exception exception) {
		return wrap(exception, null);
	}

	public BackendException setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
		return this;
	}

//	public Map<String, Object> getProperties() {
//		return properties;
//	}
//
//	@SuppressWarnings("unchecked")
//	public <T> T get(String name) {
//		return (T) properties.get(name);
//	}
//
//	public BackendException set(String name, Object value) {
//		properties.put(name, value);
//		return this;
//	}

//	public void printStackTrace(PrintStream s) {
//		synchronized (s) {
//			printStackTrace(new PrintWriter(s));
//		}
//	}

//	public void printStackTrace(PrintWriter s) {
//		synchronized (s) {
//			s.println(this);
//			s.println("\t-------------------------------");
//			if (errorCode != null) {
//				s.println("\t" + errorCode + ":" + errorCode.getClass().getName());
//			}
//			for (String key : properties.keySet()) {
//				s.println("\t" + key + "=[" + properties.get(key) + "]");
//			}
//			s.println("\t-------------------------------");
//			StackTraceElement[] trace = getStackTrace();
//			for (int i = 0; i < trace.length; i++)
//				s.println("\tat " + trace[i]);
//
//			Throwable ourCause = getCause();
//			if (ourCause != null) {
//				ourCause.printStackTrace(s);
//			}
//			s.flush();
//		}
//	}

	public BackendException setErrorId(String errorId) {
		this.errorId = errorId;
		return this;
	}

	public LinkedList<ExecutionContext> getExecutionContexts() {
		return executionContexts;
	}

	public BackendException addExecutionContext(ExecutionContext executionContext) {
		this.executionContexts.add(executionContext);
		return this;
	}

	public BackendException setDebugMessageParams(Object[] debugMessageParams) {
		this.debugMessageParams = debugMessageParams;
		return this;
	}

	public BackendException setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
		return this;
	}

}
