package hex.arch.topologyinventory.framework.adapter.in;

import hex.arch.topologyinventory.application.port.in.RouterManagementInputPort;
import hex.arch.topologyinventory.application.use_case.RouterManagementUseCase;

import hex.arch.topologyinventory.domain.entity.CoreRouter;
import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import hex.arch.topologyinventory.framework.adapter.out.h2.RouterManagementH2OutputAdapter;

public class RouterManagementGenericInputAdapter {

    private RouterManagementUseCase useCase;

    public RouterManagementGenericInputAdapter() {
        setPorts();
    }

    /**
     * GET /router/retrieve/{id}
     */
    public Router retrieveRouter(Id id) {
        return useCase.retrieveRouter(id);
    }

    /**
     * GET /router/remove/{id}
     */
    public Router removeRouter(Id id) {
        return useCase.removeRouter(id);
    }

    /**
     * POST /router/create
     */
    public Router createRouter(
        Vendor vendor, Model model, IP ip,
        Location location, RouterType routerType
    ) {
        Router router = useCase.createRouter(
            vendor, model, ip, location, routerType
        );
        return useCase.persistRouter(router);
    }

    /**
     * POST /router/add
     */
    public Router addRouterToCoreRouter(Id routerId, Id coreRouterId) {
        Router router = useCase.retrieveRouter(routerId);
        CoreRouter coreRouter = (CoreRouter) useCase.retrieveRouter(
            coreRouterId
        );
        return useCase.addRouterToCoreRouter(router, coreRouter);
    }

    /**
     * POST /router/remove
     */
    public Router removeRouterFromCoreRouter(
        Id routerId, Id coreRouterId
    ) {
        Router router = useCase.retrieveRouter(routerId);
        CoreRouter coreRouter = (CoreRouter) useCase.retrieveRouter(
            coreRouterId
        );
        return useCase.removeRouterFromCoreRouter(router, coreRouter);
    }

    private void setPorts() {
        this.useCase = new RouterManagementInputPort(
            RouterManagementH2OutputAdapter.getInstance()
        );
    }

}
