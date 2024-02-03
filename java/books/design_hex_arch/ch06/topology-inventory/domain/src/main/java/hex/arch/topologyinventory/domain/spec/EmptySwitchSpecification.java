package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.exception.GenericSpecificationException;
import hex.arch.topologyinventory.domain.spec.shared.AbstractSpecification;

public final class EmptySwitchSpecification
    extends AbstractSpecification<EdgeRouter> {

    @Override
    public boolean isSatisfiedBy(EdgeRouter edgeRouter) {
        return edgeRouter.getSwitches() == null
                || edgeRouter.getSwitches().isEmpty();
    }

    @Override
    public void check(EdgeRouter edgeRouter) {
        if (!isSatisfiedBy(edgeRouter)) {
            throw new GenericSpecificationException(
                "It isn't allowed to remove an edge router with a switch attached to it"
            );
        }
    }

}
