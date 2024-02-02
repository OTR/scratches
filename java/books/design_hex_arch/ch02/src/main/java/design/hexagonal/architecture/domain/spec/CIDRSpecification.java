package design.hexagonal.architecture.domain.spec;

public final class CIDRSpecification extends AbstractSpecification<Integer> {

    private static final int MINIMUM_ALLOWED_CIDR = 8;

    @Override
    public boolean isSatisfiedBy(Integer cidr) {
        return cidr > MINIMUM_ALLOWED_CIDR;
    }

}
