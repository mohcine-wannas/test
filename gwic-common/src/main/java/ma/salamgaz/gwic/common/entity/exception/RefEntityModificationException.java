package ma.salamgaz.gwic.common.entity.exception;

public class RefEntityModificationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RefEntityModificationException() {
    }

    public RefEntityModificationException(String message) {
        super(message);
    }

    public RefEntityModificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RefEntityModificationException(Throwable cause) {
        super(cause);
    }

    public RefEntityModificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
