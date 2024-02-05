package otr.slug.domain.exception;

public class BaseCustomException extends RuntimeException {

    public BaseCustomException() {}

    public BaseCustomException(String message) {
        super(message);
    }

    public BaseCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseCustomException(Throwable cause) {
        super(cause);
    }

}
