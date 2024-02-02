package hexagonal.architecture.domain.service;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.spec.CIDRSpecification;
import hexagonal.architecture.domain.spec.NetworkAmountSpecification;
import hexagonal.architecture.domain.spec.NetworkAvailabilitySpecification;
import hexagonal.architecture.domain.spec.RouterTypeSpecification;
import hexagonal.architecture.domain.spec.Specification;
import hexagonal.architecture.domain.vo.Network;

public class NetworkOperationService {

    private final int MINIMUM_ALLOWED_CIDR = 8;

    public static Router createNewNetwork(Router router, Network network) {
        Specification<Router> availabilitySpec = new NetworkAvailabilitySpecification(
                network.address(), network.name(), network.cidr()
        );
        Specification<Integer> cidrSpec = new CIDRSpecification();
        Specification<Router> routerTypeSpec = new RouterTypeSpecification();
        Specification<Router> amountSpec = new NetworkAmountSpecification();

        if (!cidrSpec.isSatisfiedBy(network.cidr())) {
            throw new IllegalArgumentException(
                "CIDR is below " + CIDRSpecification.MINIMUM_ALLOWED_CIDR
            );
        }

        if (!availabilitySpec.isSatisfiedBy(router)) {
            throw new IllegalArgumentException("Address already exist");
        }

        if (amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network newNetwork = router.createNetwork(
                    network.address(),
                    network.name(),
                    network.cidr()
            );
            router.addNetworkToSwitch(newNetwork);
        }

        return router;
    }

}
