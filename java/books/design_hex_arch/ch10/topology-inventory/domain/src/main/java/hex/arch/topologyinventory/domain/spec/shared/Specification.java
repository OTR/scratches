package hex.arch.topologyinventory.domain.spec.shared;

import hex.arch.topologyinventory.domain.exception.GenericSpecificationException;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    public abstract void check(T t) throws GenericSpecificationException;

    Specification<T> and(Specification<T> specification);

}
