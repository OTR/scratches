package design.hexagonal.architecture.domain.spec;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.spec.shared.AbstractSpecification;
import design.hexagonal.architecture.domain.vo.IP;
import design.hexagonal.architecture.domain.vo.Network;

public class NetworkAvailabilitySpecification extends AbstractSpecification<Router> {

    private final IP address;
    private final String name;
    private final int cidr;

    public NetworkAvailabilitySpecification(IP address, String name, int cidr) {
        this.address = address;
        this.name = name;
        this.cidr = cidr;
    }

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router != null && isNetworkAvailable(router);
    }

    private boolean isNetworkAvailable(Router router) {
        return router.retrieveNetworks().stream()
            .noneMatch(network ->
                network.address().equals(address) &&
                network.name().equals(name) &&
                network.cird() == cidr
            );
    }

}
