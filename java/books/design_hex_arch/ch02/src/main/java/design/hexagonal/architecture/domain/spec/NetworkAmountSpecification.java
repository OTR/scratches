package design.hexagonal.architecture.domain.spec;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.spec.shared.AbstractSpecification;

public class NetworkAmountSpecification extends AbstractSpecification<Router> {

    private static final int MAXIMUM_ALLOWED_NETWORKS = 6;

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.retrieveNetworks().size() <= MAXIMUM_ALLOWED_NETWORKS;
    }

}
