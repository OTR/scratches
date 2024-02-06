package otr.slug.domain.spec;

import otr.slug.domain.exception.GenericSpecificationException;
import otr.slug.domain.spec.shared.AbstractSpecification;

import java.time.Year;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check if the input contains a valid year between 2000 and the current year
 */
public class ValidYearSpecification extends AbstractSpecification<String> {

    private static final int CURRENT_YEAR = Year.now().getValue();
    private static final int LOWER_BOUNDARY = 2000;
    private static final int UPPER_BOUNDARY = CURRENT_YEAR;
    private static final Pattern ANY_DIGIT_PATTERN = Pattern.compile("\\d+");
    private static final boolean GREATER_THAN_MAX_INT = false;

    @Override
    public boolean isSatisfiedBy(String s) {
        Matcher matcher = ANY_DIGIT_PATTERN.matcher(s);

        return matcher.results()
            .map(MatchResult::group)
            .allMatch(i -> {
                try {
                    int parsed = Integer.parseInt(i);
                    return LOWER_BOUNDARY <= parsed && parsed <= UPPER_BOUNDARY;
                } catch (NumberFormatException e) {
                    return GREATER_THAN_MAX_INT;
                }
            });

    }

    @Override
    public void check(String s) throws GenericSpecificationException {
        if (!isSatisfiedBy(s)) {
            throw new GenericSpecificationException(
                "Only digits that look like a valid publishing year " +
                "are allowed in Slug"
            );
        }
    }

}
