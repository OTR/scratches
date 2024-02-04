package otr.slug.domain.spec;

import otr.slug.domain.exception.GenericSpecificationException;
import otr.slug.domain.spec.shared.AbstractSpecification;
import otr.slug.domain.vo.Slug;

import java.time.Year;

/**
 * Check if the input contains a valid year between 2000 and the current year
 */
public class ValidYearSpecification extends AbstractSpecification<Slug> {

    @Override
    public boolean isSatisfiedBy(Slug slug) {
        try {
            int year = Integer.parseInt(slug.value());
            int currentYear = Year.now().getValue();
            return year >= 2000 && year <= currentYear;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void check(Slug slug) throws GenericSpecificationException {
        if (!isSatisfiedBy(slug)) {
            throw new GenericSpecificationException(
                "Only digits that look like a valid publishing year " +
                "are allowed in Slug"
            );
        }
    }

}
