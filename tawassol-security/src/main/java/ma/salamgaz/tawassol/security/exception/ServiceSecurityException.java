package ma.salamgaz.tawassol.security.exception;

import lombok.Getter;
import ma.salamgaz.tawassol.common.exception.ErrorMessageType;

@Getter
@SuppressWarnings("serial")
public class ServiceSecurityException extends RuntimeException {

    private final ErrorMessageType error;

    public ErrorMessageType getError() {
		return error;
	}

	public ServiceSecurityException(ErrorMessageType error) {
        super(error.getMessage());
        this.error = error;
    }

    public ServiceSecurityException(ErrorMessageType error, String message) {
        super(message);
        this.error = error;
    }

    public ServiceSecurityException(ErrorMessageType error, Throwable e) {
        super(error.getMessage(), e);
        this.error = error;
    }

    public ServiceSecurityException(ErrorMessageType error, String message, Throwable e) {
        super(message, e);
        this.error = error;
    }
}
