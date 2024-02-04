package hex.arch.topologyinventory.application.port.in;

import hex.arch.topologyinventory.application.port.out
    .RouterManagementOutputPort;
import hex.arch.topologyinventory.application.use_case
    .NetworkManagementUseCase;
import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.service.NetworkService;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Network;

import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@NoArgsConstructor
public class NetworkManagementInputPort
    implements NetworkManagementUseCase {

    private RouterManagementOutputPort outputPort;

    public NetworkManagementInputPort(
        RouterManagementOutputPort outputPort
    ) {
        this.outputPort = outputPort;
    }

    @Override
    public Network createNetwork(
        IP networkAddress, String networkName, int networkCidr
    ) {
        return Network.builder()
            .networkAddress(networkAddress)
            .networkName(networkName)
            .networkCidr(networkCidr)
            .build();
    }

    @Override
    public Switch addNetworkToSwitch(
        Network network, Switch networkSwitch
    ) {
        networkSwitch.addNetworkToSwitch(network);
        return networkSwitch;
    }

    @Override
    public Switch removeNetworkFromSwitch(
        String networkName, Switch networkSwitch
    ) {
        Id routerId = networkSwitch.getId();
        Id switchId = networkSwitch.getId();
        EdgeRouter edgeRouter = (EdgeRouter) outputPort
            .retrieveRouter(routerId);
        Switch switchToRemoveNetwork = edgeRouter
            .getSwitches()
            .get(switchId);
        Predicate<Network> networkPredicate = Network
            .getNetworkNamePredicate(networkName);
        var network = NetworkService.
            findNetwork(
                switchToRemoveNetwork.getSwitchNetworks(),
                networkPredicate
            );
        switchToRemoveNetwork.removeNetworkFromSwitch(network);
        outputPort.persistRouter(edgeRouter);
        return switchToRemoveNetwork.removeNetworkFromSwitch(network)
            ? switchToRemoveNetwork
            : null;
    }

}
