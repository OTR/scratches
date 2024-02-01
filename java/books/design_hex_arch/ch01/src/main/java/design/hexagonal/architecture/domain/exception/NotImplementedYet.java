package design.hexagonal.architecture.domain.exception;

public class NotImplementedYet extends UnsupportedOperationException {

    public NotImplementedYet() {
        super("Not implemented yet");
    }

    public NotImplementedYet(String message) {
        super(message);
    }

}
