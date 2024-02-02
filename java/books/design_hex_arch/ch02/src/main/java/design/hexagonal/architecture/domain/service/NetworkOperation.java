package design.hexagonal.architecture.domain.service;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.exception.NotImplementedYet;
import design.hexagonal.architecture.domain.vo.IP;
import design.hexagonal.architecture.domain.vo.Network;

public class NetworkOperation {

    private final int MINIMUM_ALLOWED_CIDR = 8;

    public void createNewNetwork(Router router, IP address, String name, int cidr) {
        if (cidr < MINIMUM_ALLOWED_CIDR) {
            throw new IllegalArgumentException(
                "CIDR is below " + MINIMUM_ALLOWED_CIDR
            );
        }

        if (isNetworkAvailable(router, address, cidr)) {
            throw new IllegalArgumentException("Address already exist");
        }

        Network network = router.createNetwork(address, name, cidr);
        router.addNetworkToSwitch(network);
    }

    private boolean isNetworkAvailable(Router router, IP address, int cidr) {
        boolean availability = true;

        for (Network network : router.retrieveNetworks()) {
            if (
                network.address().equals(address) &&
                network.cird() == cidr
            ) {
                availability = false;
                break;
            }
        }

        return availability;
    }

}
