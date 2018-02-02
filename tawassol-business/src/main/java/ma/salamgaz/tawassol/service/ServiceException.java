package ma.salamgaz.tawassol.service;

import lombok.Getter;
import ma.salamgaz.tawassol.common.exception.ErrorMessageMapper;
import ma.salamgaz.tawassol.common.exception.ErrorMessageType;

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

}
