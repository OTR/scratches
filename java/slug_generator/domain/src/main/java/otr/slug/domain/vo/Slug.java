package otr.slug.domain.vo;

import otr.slug.domain.spec.AlphaDecimalSpecification;
import otr.slug.domain.spec.ValidYearSpecification;
import otr.slug.domain.spec.shared.Specification;

public record Slug(
    String value
) {

    public Slug(String value) {
        Specification<String> alphaDecimal = new AlphaDecimalSpecification();
        Specification<String> validYear = new ValidYearSpecification();

        alphaDecimal.check(value);
        validYear.check(value);

        this.value = value;
    }

}
