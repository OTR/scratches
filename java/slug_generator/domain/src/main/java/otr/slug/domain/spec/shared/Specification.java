package otr.slug.domain.spec.shared;

/**
 *
 */
public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    Specification<T> and(Specification<T> specification);

}
