package hex.arch.topologyinventory.application.port.in;

import hex.arch.topologyinventory.application.port.out.RouterManagementOutputPort;
import hex.arch.topologyinventory.application.use_case.NetworkManagementUseCase;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Network;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NetworkManagementInputPort implements NetworkManagementUseCase {

    private RouterManagementOutputPort outputPort;

    public NetworkManagementInputPort(RouterManagementOutputPort outputPort) {
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
    public Switch addNetworkToSwitch(Network network, Switch networkSwitch) {
        networkSwitch.addNetworkToSwitch(network);
        return networkSwitch;
    }

    @Override
    public Switch removeNetworkFromSwitch(
        Network network, Switch networkSwitch
    ) {
        networkSwitch.removeNetworkFromSwitch(network);
        return networkSwitch;
    }

}
