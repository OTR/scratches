package otr.slug.framework.exception;

import org.apache.commons.cli.UnrecognizedOptionException;
import otr.slug.domain.exception.BaseCustomException;

import java.util.stream.Stream;

public class UnrecognizedOptionHandler {

    private static final String TRIGGER_MESSAGE = "Unrecognized option:";

    public static String[] handle(
        String[] args,
        UnrecognizedOptionException e
    ) {
        String message = e.getMessage();
        String brokenOption = e.getOption();
        if (message.contains(TRIGGER_MESSAGE)) {
            if (brokenOption.matches("-+")) {
                // TODO
                String[] newArgs = Stream.of(args)
                    .map(s -> s.replace(brokenOption, ""))
                    .toArray(String[]::new);

                return newArgs;
            } else {
                throw new BaseCustomException(e);
            }
        } else {
            throw new BaseCustomException(e);
        }
    }

}
