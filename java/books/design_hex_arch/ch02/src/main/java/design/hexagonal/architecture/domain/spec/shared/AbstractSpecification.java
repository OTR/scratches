package design.hexagonal.architecture.domain.spec.shared;

public abstract class AbstractSpecification<T> implements Specification<T> {

    @Override
    public abstract boolean isSatisfiedBy(T t);

    @Override
    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }

}
