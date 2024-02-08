package hex.arch.topologyinventory.application.use_case;

import hex.arch.topologyinventory.application.port.out.RouterManagementOutputPort;
import hex.arch.topologyinventory.domain.entity.CoreRouter;
import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.Vendor;

public interface RouterManagementUseCase {

    Router createRouter(
//        Id id,
        Vendor vendor,
        Model model,
        IP ip,
        Location location,
        RouterType routerType
    );

    Router removeRouter(Id id);

    CoreRouter addRouterToCoreRouter(Router router, CoreRouter coreRouter);

    Router removeRouterFromCoreRouter(Router router, CoreRouter coreRouter);

    Router retrieveRouter(Id id);

    Router persistRouter(Router router);

    void setOutputPort(RouterManagementOutputPort outputPort);

}
