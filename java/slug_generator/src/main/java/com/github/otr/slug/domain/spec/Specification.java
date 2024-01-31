package com.github.otr.slug.domain.spec;

/**
 *
 */
public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    Specification<T> and(Specification<T> specification);

}
