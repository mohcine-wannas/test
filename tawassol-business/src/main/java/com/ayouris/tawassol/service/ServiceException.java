package com.ayouris.tawassol.service;

import lombok.Getter;
import com.ayouris.tawassol.common.exception.ErrorMessageMapper;
import com.ayouris.tawassol.common.exception.ErrorMessageType;

@Getter
@SuppressWarnings("serial")
public class ServiceException extends RuntimeException  implements ErrorMessageMapper {

    private final ErrorMessageType error;

    public ServiceException(ErrorMessageType error) {
        super(error.getMessage());
        this.error = error;
    }

    public ServiceException(ErrorMessageType error, String message) {
        super(message);
        this.error = error;
    }

    public ServiceException(ErrorMessageType error, String message, Throwable e) {
        super(message, e);
        this.error = error;
    }

    @Override
    public ErrorMessageType getError() {
        return error;
    }
}
