package com.github.otr.home_lib.upload_management.domain.specification;

/**
 *
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    @Override
    public abstract boolean isSatisfiedBy(T t);

    @Override
    public Specification<T> and(final Specification<T> specification) {
        return null;
    }
}
