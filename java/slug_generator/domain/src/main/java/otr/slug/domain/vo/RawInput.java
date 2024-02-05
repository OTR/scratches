package otr.slug.domain.vo;

import static java.util.Objects.requireNonNull;

public record RawInput (
    String value
) {

    public RawInput {
        requireNonNull(value, "Value shouldn't be NULL");
    }

}
