package otr.slug.domain.spec;

import otr.slug.domain.exception.GenericSpecificationException;
import otr.slug.domain.spec.shared.AbstractSpecification;

/**
 * Check if the input contains only Latin letters in lowercase
 */
public class AlphaDecimalSpecification extends AbstractSpecification<String> {


    @Override
    public boolean isSatisfiedBy(String s) {
        return s.matches("[a-z0-9_]+");
    }

    @Override
    public void check(String s) throws GenericSpecificationException {
        if (!isSatisfiedBy(s)) {
            throw new GenericSpecificationException(
                "Slug should contain only alphabetical or decimal " +
                "or underscore characters"
            );
        }
    }

}
