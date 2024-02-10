package otr.elib.domain.exception;

public class BaseAppException extends RuntimeException {

    public BaseAppException() {
    }

    public BaseAppException(String message) {
        super(message);
    }

    public BaseAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseAppException(Throwable cause) {
        super(cause);
    }

    public BaseAppException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
