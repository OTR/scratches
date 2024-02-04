package otr.slug.domain.vo;

import otr.slug.domain.spec.AlphaDecimalSpecification;
import otr.slug.domain.spec.ValidYearSpecification;
import otr.slug.domain.spec.shared.Specification;

public record Slug(
    String value
) {

    public Slug(String value) {
        Specification<Slug> alphaDecimal = new AlphaDecimalSpecification();
        Specification<Slug> validYear = new ValidYearSpecification();



        this.value = value;
    }

}
