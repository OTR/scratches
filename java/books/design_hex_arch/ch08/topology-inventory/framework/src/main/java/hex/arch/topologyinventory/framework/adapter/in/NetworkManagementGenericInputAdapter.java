package hex.arch.topologyinventory.framework.adapter.in;

import hex.arch.topologyinventory.application.port.in.NetworkManagementInputPort;
import hex.arch.topologyinventory.application.port.in.SwitchManagementInputPort;
import hex.arch.topologyinventory.application.use_case.NetworkManagementUseCase;
import hex.arch.topologyinventory.application.use_case.SwitchManagementUseCase;

import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Network;

import hex.arch.topologyinventory.framework.adapter.out.h2.RouterManagementH2OutputAdapter;
import hex.arch.topologyinventory.framework.adapter.out.h2.SwitchManagementH2OutputAdapter;

public class NetworkManagementGenericInputAdapter {

    private SwitchManagementUseCase switchUseCase;
    private NetworkManagementUseCase networkUseCase;

    public NetworkManagementGenericInputAdapter() {
        setPorts();
    }

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

    private void setPorts() {
        this.switchUseCase = new SwitchManagementInputPort(
            SwitchManagementH2OutputAdapter.getInstance()
        );
        this.networkUseCase = new NetworkManagementInputPort(
            RouterManagementH2OutputAdapter.getInstance()
        );
    }

}
