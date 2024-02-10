package otr.elib.exception;

/**
 * My Custom Exception for handling `INT` Signal
 * i.e. Ctrl+C Keys pressed
 */
public class KeyboardInterruptException extends Exception {

    public KeyboardInterruptException() {
        super("KeyboardInterrupt received");
    }

}
