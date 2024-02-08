package hex.arch.topologyinventory.domain.spec.shared;

import hex.arch.topologyinventory.domain.exception.GenericSpecificationException;

public class AndSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;
    private final Specification<T> spec2;

    public AndSpecification(
        Specification<T> spec1,
        Specification<T> spec2
    ) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(final T t) {
        return spec1.isSatisfiedBy(t)
                && spec2.isSatisfiedBy(t);
    }

    @Override
    public void check(T t) throws GenericSpecificationException {
        if (!isSatisfiedBy(t)) {
            throw new GenericSpecificationException("");
        }
    }

}
