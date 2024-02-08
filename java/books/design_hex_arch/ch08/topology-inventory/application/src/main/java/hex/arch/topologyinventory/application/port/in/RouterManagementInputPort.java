package hex.arch.topologyinventory.application.port.in;

import hex.arch.topologyinventory.application.port.out.RouterManagementOutputPort;
import hex.arch.topologyinventory.application.use_case.RouterManagementUseCase;

import hex.arch.topologyinventory.domain.entity.factory.RouterFactory;
import hex.arch.topologyinventory.domain.entity.CoreRouter;
import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import lombok.NoArgsConstructor;

import java.security.GeneralSecurityException;

@NoArgsConstructor
public class RouterManagementInputPort implements RouterManagementUseCase {

    private RouterManagementOutputPort outputPort;

    public RouterManagementInputPort(RouterManagementOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public Router createRouter(
        Vendor vendor, Model model, IP ip,
        Location location, RouterType routerType
    ) {
        return RouterFactory.getRouter(
            null,
            vendor,
            model,
            ip,
            location,
            routerType
        );
    }

    @Override
    public Router removeRouter(Id id) {
        return outputPort.removeRouter(id);
    }

    @Override
    public CoreRouter addRouterToCoreRouter(
        Router router, CoreRouter coreRouter
    ) {
        CoreRouter addedRouter = coreRouter.addRouter(router);
        // persistRouter(addedRouter);
        return addedRouter;
    }

    @Override
    public Router removeRouterFromCoreRouter(
        Router router, CoreRouter coreRouter
    ) {
        Router removedRouter = coreRouter.removeRouter(router);
        persistRouter(coreRouter);
        return removedRouter;
    }

    @Override
    public Router retrieveRouter(Id id) {
        return outputPort.retrieveRouter(id);
    }

    @Override
    public Router persistRouter(Router router) {
        return outputPort.persistRouter(router);
    }
}
