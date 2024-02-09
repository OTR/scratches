package hex.arch.topologyinventory.application.use_case;

import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Network;

public interface NetworkManagementUseCase {

    Network createNetwork(
        IP networkAddress, String networkName, int networkCidr
    );

    Switch addNetworkToSwitch(Network network, Switch networkSwitch);

    Switch removeNetworkFromSwitch(String network, Switch networkSwitch);

}
