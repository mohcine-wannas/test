package com.ayouris.tawassol.rest.exception;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GlobalException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8697525975452434452L;
	/**
	 * 
	 */
	public final String FOREIGN_KEY_VIOLATION = "23503";
	public final String UNIQUE_VIOLATION = "23505";
	public final String NOT_NULL_VIOLATION = "23502";

	private String message;
	private HttpStatus status;

	public GlobalException() {

	}

	public GlobalException(Exception e, MessageSource messageSource) {
		status = HttpStatus.INTERNAL_SERVER_ERROR;
		if (e instanceof BusinessRuleException) {
			BusinessRuleException businessRuleException = (BusinessRuleException) e;
			status = HttpStatus.CONFLICT;
			message = messageSource.getMessage(businessRuleException.getMessage(), businessRuleException.getParams(), null);
		} else if (e instanceof EntityNotFoundException) {
			EntityNotFoundException entityNotFoundException = (EntityNotFoundException) e;
			status = HttpStatus.NOT_FOUND;
			message = messageSource.getMessage(entityNotFoundException.getMessage(), entityNotFoundException.getParams(), null);
//		} else if (e instanceof EntityAlreadyExistsException) {
//			EntityAlreadyExistsException entityAlreadyExistsException = (EntityAlreadyExistsException) e;
//			status = HttpStatus.CONFLICT;
//			message = messageSource.getMessage(entityAlreadyExistsException.getMessage(), entityAlreadyExistsException.getParams(), null);
		} else if (e instanceof BadCredentialsException) {
			status = HttpStatus.UNAUTHORIZED;
			message = messageSource.getMessage("auth.message.error", null, null);
		} else if (e instanceof UsernameNotFoundException) {
			status = HttpStatus.NOT_FOUND;
			message = messageSource.getMessage("auth.message.error", null, null);
			status = HttpStatus.UNAUTHORIZED;
		} else if (e instanceof AccessDeniedException) {
			message = messageSource.getMessage("accesControle.message.texte", null, null);
			status = HttpStatus.UNAUTHORIZED;
		} else if (e instanceof DisabledException) {
			message = messageSource.getMessage("auth.message.error", null, null);
			status = HttpStatus.UNAUTHORIZED;
		} else if (e instanceof AuthenticationServiceException) {
			message = messageSource.getMessage("accesDatabase.message.texte", null, null);
			status = HttpStatus.UNAUTHORIZED;
		} else if (e instanceof AuthenticationCredentialsNotFoundException) {
			message = messageSource.getMessage("auth.message.error", null, null);
			status = HttpStatus.UNAUTHORIZED;
		} else {
			//message = messageSource.getMessage("erreur.message.texte", null, null); //TODO
			message = "erreur.message.text";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
			ConstraintViolationException ex = null;
			if (e instanceof ConstraintViolationException) {
				ex = (ConstraintViolationException) e;
			} else if (e instanceof DataIntegrityViolationException) {
				DataIntegrityViolationException integrityViolationException = (DataIntegrityViolationException) e;
				if (integrityViolationException.getCause() instanceof ConstraintViolationException) {
					ex = (ConstraintViolationException) integrityViolationException.getCause();
				}
			}
			if (ex != null) {
				String codeError = getErrorCode(ex);
				if (codeError.equals(FOREIGN_KEY_VIOLATION)) {
					message = ex.getCause().getMessage();
				} else if (codeError.equals(UNIQUE_VIOLATION)) {
					message =  ex.getCause().getMessage();
				} else if (codeError.equals(NOT_NULL_VIOLATION)) {
					message = ex.getCause().getMessage();
				} else {
					System.out.println("codeErreur : " + codeError);
					e.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}

	}

	public String getErrorCode(ConstraintViolationException ex) {
		Throwable errs = ((ConstraintViolationException) ex).getCause();
		return ((SQLException) errs).getSQLState();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}