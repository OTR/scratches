package otr.slug.framework.exception;

import org.apache.commons.cli.UnrecognizedOptionException;
import otr.slug.domain.exception.BaseCustomException;

import java.util.stream.Stream;

public class UnrecognizedOptionHandler {

    private static final String TRIGGER_MESSAGE = "Unrecognized option:";
    private static final String LEADING_DASHES = "-+.*";
    private static final String MULTIPLE_DASHES = "-+";

    public static String[] handle(
        String[] args,
        UnrecognizedOptionException e
    ) {
        String message = e.getMessage();
        String brokenOption = e.getOption();
        if (message.contains(TRIGGER_MESSAGE)) {
            if (brokenOption.matches(LEADING_DASHES)) {
                return removeDashOnlyStrings(args);
            } else {
                throw new BaseCustomException(e);
            }
        } else {
            throw new BaseCustomException(e);
        }
    }

    private static String[] removeDashOnlyStrings(String[] args) {
        String[] newArgs = Stream.of(args)
            .map(s ->
                s.matches(LEADING_DASHES)
                    ? s.replaceAll(MULTIPLE_DASHES, "")
                    : s
            )
            .filter(s -> !s.isEmpty())
            .toArray(String[]::new);

        return newArgs;
    }

}
