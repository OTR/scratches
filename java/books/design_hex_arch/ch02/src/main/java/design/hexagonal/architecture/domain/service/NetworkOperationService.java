package design.hexagonal.architecture.domain.service;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.spec.CIDRSpecification;
import design.hexagonal.architecture.domain.spec.NetworkAmountSpecification;
import design.hexagonal.architecture.domain.spec.NetworkAvailabilitySpecification;
import design.hexagonal.architecture.domain.spec.RouterTypeSpecification;
import design.hexagonal.architecture.domain.spec.Specification;
import design.hexagonal.architecture.domain.vo.Network;

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
