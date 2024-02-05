package otr.slug.domain.vo;

import static java.util.Objects.requireNonNull;

/**
 * Serves as `Command object`
 * Just a wrapper class for raw user input received as String
 */
public record RawInput (
    String value
) {

    public RawInput {
        requireNonNull(value, "Value shouldn't be NULL");
    }

}
