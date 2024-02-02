package hexagonal.architecture.domain.spec;

public abstract sealed class AbstractSpecification<T>
        implements Specification<T>
        permits  AndSpecification,
                CIDRSpecification,
                NetworkAmountSpecification,
                NetworkAvailabilitySpecification,
                RouterTypeSpecification
{

    @Override
    public abstract boolean isSatisfiedBy(T t);

    @Override
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }

}
