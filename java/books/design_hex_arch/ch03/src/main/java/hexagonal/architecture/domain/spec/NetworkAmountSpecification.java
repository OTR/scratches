package hexagonal.architecture.domain.spec;

import hexagonal.architecture.domain.entity.Router;

public final class NetworkAmountSpecification extends AbstractSpecification<Router> {

    private static final int MAXIMUM_ALLOWED_NETWORKS = 6;

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.retrieveNetworks().size() <= MAXIMUM_ALLOWED_NETWORKS;
    }

}
