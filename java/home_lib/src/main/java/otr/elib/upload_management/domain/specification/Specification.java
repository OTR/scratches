package otr.elib.upload_management.domain.specification;

/**
 *
 */
public interface Specification<T> {
    boolean isSatisfiedBy(T candidate);

    Specification<T> and(Specification<T> specification);

}
