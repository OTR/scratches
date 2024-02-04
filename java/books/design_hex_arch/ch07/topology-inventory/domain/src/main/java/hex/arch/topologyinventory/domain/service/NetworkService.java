package hex.arch.topologyinventory.domain.service;

import hex.arch.topologyinventory.domain.vo.Network;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NetworkService {

    public static List<Network> filterAndRetrieveNetworks(
        List<Network> networks, Predicate<Network> networkPredicate
    ) {
        return networks.stream()
            .filter(networkPredicate)
            .collect(Collectors.toList());
    }

    public static Network findNetwork(
        List<Network> networks, Predicate<Network> networkPredicate
    ){
        return networks
            .stream()
            .filter(networkPredicate)
            .findFirst().orElse(null);
    }

}
