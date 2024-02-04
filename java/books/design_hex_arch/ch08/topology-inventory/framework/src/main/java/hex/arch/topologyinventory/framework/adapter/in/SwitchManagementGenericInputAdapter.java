package hex.arch.topologyinventory.framework.adapter.in;

import hex.arch.topologyinventory.application.port.in.RouterManagementInputPort;
import hex.arch.topologyinventory.application.port.in.SwitchManagementInputPort;
import hex.arch.topologyinventory.application.use_case.RouterManagementUseCase;
import hex.arch.topologyinventory.application.use_case.SwitchManagementUseCase;

import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.Id;

import hex.arch.topologyinventory.framework.adapter.out.h2.RouterManagementH2OutputAdapter;
import hex.arch.topologyinventory.framework.adapter.out.h2.SwitchManagementH2OutputAdapter;

public class SwitchManagementGenericInputAdapter {

    private SwitchManagementUseCase switchUseCase;
    private RouterManagementUseCase routerUseCase;

    public SwitchManagementGenericInputAdapter() {
        setPorts();
    }

    /**
     * GET /switch/retrieve/{id}
     */
    public Switch retrieveSwitch(Id switchId) {
        return switchUseCase.retrieveSwitch(switchId);
    }

    private void setPorts() {
        this.switchUseCase = new SwitchManagementInputPort(
            SwitchManagementH2OutputAdapter.getInstance()
        );
        this.routerUseCase = new RouterManagementInputPort(
            RouterManagementH2OutputAdapter.getInstance()
        );
    }

}
