package hex.arch.topologyinventory.framework.adapter.in;

import hex.arch.topologyinventory.application.use_case.RouterManagementUseCase;
import hex.arch.topologyinventory.application.use_case.SwitchManagementUseCase;

import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.SwitchType;
import hex.arch.topologyinventory.domain.vo.Vendor;

public class SwitchManagementGenericInputAdapter {

    private RouterManagementUseCase routerUseCase;
    private SwitchManagementUseCase switchUseCase;

    public SwitchManagementGenericInputAdapter(
        RouterManagementUseCase routerUseCase,
        SwitchManagementUseCase switchUseCase
    ) {
        this.routerUseCase = routerUseCase;
        this.switchUseCase = switchUseCase;
    }

    /**
     * GET /switch/retrieve/{id}
     * */
    public Switch retrieveSwitch(Id switchId) {
        return switchUseCase.retrieveSwitch(switchId);
    }

    /**
     * POST /switch/create
     * */
    public EdgeRouter createAndAddSwitchToEdgeRouter(
        Vendor vendor,
        Model model,
        IP ip,
        Location location,
        SwitchType switchType,
        Id routerId
    ) {
        Switch newSwitch = switchUseCase.createSwitch(
            vendor, model, ip, location, switchType
        );
        Router edgeRouter = routerUseCase.retrieveRouter(routerId);

        if (!edgeRouter.getRouterType().equals(RouterType.EDGE)) {
            throw new UnsupportedOperationException(
                "Please inform the Id of an Edge router to add a switch"
            );
        }

        Router router = switchUseCase.addSwitchToEdgeRouter(
            newSwitch, (EdgeRouter) edgeRouter
        );

        return (EdgeRouter) routerUseCase.persistRouter(router);
    }

    /**
     * POST /switch/remove
     * */
    public EdgeRouter removeSwitchFromEdgeRouter(Id switchId, Id edgeRouterId) {
        EdgeRouter edgeRouter = (EdgeRouter) routerUseCase
            .retrieveRouter(edgeRouterId);
        Switch networkSwitch = edgeRouter.getSwitches().get(switchId);
        Router router = switchUseCase.removeSwitchFromEdgeRouter(
            networkSwitch, edgeRouter
        );
        return (EdgeRouter) routerUseCase.persistRouter(router);
    }

}
