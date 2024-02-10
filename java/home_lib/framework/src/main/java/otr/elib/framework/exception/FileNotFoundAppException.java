package otr.elib.framework.exception;

import otr.elib.domain.exception.BaseAppException;

public class FileNotFoundAppException extends BaseAppException {

    public FileNotFoundAppException() {
    }

    public FileNotFoundAppException(String message) {
        super(message);
    }

    public FileNotFoundAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotFoundAppException(Throwable cause) {
        super(cause);
    }

    public FileNotFoundAppException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
