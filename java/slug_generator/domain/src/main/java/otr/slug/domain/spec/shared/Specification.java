package otr.slug.domain.spec.shared;

import otr.slug.domain.exception.GenericSpecificationException;

/**
 *
 */
public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    Specification<T> and(Specification<T> specification);

    void check(T t) throws GenericSpecificationException;

}
