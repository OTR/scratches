package otr.slug.domain.spec;

import otr.slug.domain.exception.GenericSpecificationException;
import otr.slug.domain.spec.shared.AbstractSpecification;
import otr.slug.domain.vo.Slug;

/**
 * Check if the input contains only Latin letters in lowercase
 */
public class AlphaDecimalSpecification extends AbstractSpecification<Slug> {

    @Override
    public boolean isSatisfiedBy(Slug slug) {
        return slug.value().matches("[a-z0-9_]+");
    }

    @Override
    public void check(Slug slug) throws GenericSpecificationException {
        if (!isSatisfiedBy(slug)) {
            throw new GenericSpecificationException(
                "Slug should contain only alphabetical or decimal " +
                "or underscore characters"
            );
        }
    }

}
