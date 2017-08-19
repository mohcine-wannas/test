package com.ayouris.tawassol.security.exception;


import lombok.Getter;
import com.ayouris.tawassol.common.exception.ErrorMessageType;


@Getter
@SuppressWarnings("serial")
public class AuthorizationException extends RuntimeException {

    private final ErrorMessageType error;

    public AuthorizationException(ErrorMessageType error) {
        super(error.getMessage());
        this.error = error;
    }

    public AuthorizationException(ErrorMessageType error, String message) {
        super(message);
        this.error = error;
    }

}
