package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.exception.GenericSpecificationException;
import hex.arch.topologyinventory.domain.spec.shared.AbstractSpecification;

public class CIDRSpecification extends AbstractSpecification<Integer> {

    public static final int MINIMUM_ALLOWED_CIDR = 8;

    @Override
    public boolean isSatisfiedBy(Integer cidr) {
        return cidr >= MINIMUM_ALLOWED_CIDR;
    }

    @Override
    public void check(Integer cidr) throws GenericSpecificationException {
        if (!isSatisfiedBy(cidr)) {
            throw new GenericSpecificationException(
                "CIDR is below " + MINIMUM_ALLOWED_CIDR
            );
        }
    }

}
