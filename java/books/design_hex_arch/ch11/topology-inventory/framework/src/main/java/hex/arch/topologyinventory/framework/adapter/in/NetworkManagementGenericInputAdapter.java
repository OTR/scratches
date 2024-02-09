package hex.arch.topologyinventory.framework.adapter.in;

import hex.arch.topologyinventory.application.use_case.NetworkManagementUseCase;
import hex.arch.topologyinventory.application.use_case.SwitchManagementUseCase;

import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Network;

import javax.inject.Inject;

public class NetworkManagementGenericInputAdapter {

    @Inject
    SwitchManagementUseCase switchUseCase;

    @Inject
    NetworkManagementUseCase networkUseCase;

    /**
     * POST /network/add
     */
    public Switch addNetworkToSwitch(Network network, Id switchId) {
        Switch networkSwitch = switchUseCase.retrieveSwitch(switchId);
        return networkUseCase.addNetworkToSwitch(network, networkSwitch);
    }

    public Switch removeNetworkFromSwitch(
        String networkName, Id switchId
    ) {
        Switch networkSwitch = switchUseCase.retrieveSwitch(switchId);
        return networkUseCase.removeNetworkFromSwitch(networkName, networkSwitch);
    }

}
