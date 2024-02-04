package hex.arch.topologyinventory.framework.adapter.in;

import hex.arch.topologyinventory.application.port.in.RouterManagementInputPort;
import hex.arch.topologyinventory.application.use_case.RouterManagementUseCase;
import hex.arch.topologyinventory.framework.adapter.out.h2.RouterManagementH2OutputAdapter;

public class RouterManagementGenericInputAdapter {

    private RouterManagementUseCase useCase;

    public RouterManagementGenericInputAdapter() {
        setPorts();
    }

    private void setPorts() {
        this.useCase = new RouterManagementInputPort(
            RouterManagementH2OutputAdapter.getInstance()
        );
    }

}
