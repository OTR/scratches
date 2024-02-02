package design.hexagonal.architecture.domain.service;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.spec.CIDRSpecification;
import design.hexagonal.architecture.domain.spec.NetworkAmountSpecification;
import design.hexagonal.architecture.domain.spec.NetworkAvailabilitySpecification;
import design.hexagonal.architecture.domain.spec.RouterTypeSpecification;
import design.hexagonal.architecture.domain.spec.Specification;
import design.hexagonal.architecture.domain.vo.IP;
import design.hexagonal.architecture.domain.vo.Network;

public class NetworkOperationService {

    private final int MINIMUM_ALLOWED_CIDR = 8;

    public void createNewNetwork(Router router, IP address, String name, int cidr) {
        Specification<Integer> cidrSpec = new CIDRSpecification();
        Specification<Router> availabilitySpec = new NetworkAvailabilitySpecification(
            address, name, cidr
        );
        Specification<Router> amountSpec = new NetworkAmountSpecification();
        Specification<Router> routerTypeSpec = new RouterTypeSpecification();

        if (!cidrSpec.isSatisfiedBy(cidr)) {
            throw new IllegalArgumentException(
                "CIDR is below " + CIDRSpecification.MINIMUM_ALLOWED_CIDR
            );
        }

        if (!availabilitySpec.isSatisfiedBy(router)) {
            throw new IllegalArgumentException("Address already exist");
        }

        if (amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network network = router.createNetwork(address, name, cidr);
            router.addNetworkToSwitch(network);
        }

    }

}
