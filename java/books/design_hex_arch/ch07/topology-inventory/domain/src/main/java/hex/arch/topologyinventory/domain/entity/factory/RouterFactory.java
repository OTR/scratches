package hex.arch.topologyinventory.domain.entity.factory;

import hex.arch.topologyinventory.domain.entity.CoreRouter;
import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.Vendor;

public class RouterFactory {

    public static Router getRouter(
        Vendor vendor, Model model, IP ip,
        Location location, RouterType routerType
    ) {
        return switch (routerType) {
            case CORE -> {
                yield CoreRouter.builder()
                    .id(Id.withoutId())
                    .vendor(vendor)
                    .model(model)
                    .ip(ip)
                    .location(location)
                    .routerType(routerType)
                    .build();
            }
            case EDGE -> {
                yield EdgeRouter.builder()
                    .id(Id.withoutId())
                    .vendor(vendor)
                    .model(model)
                    .ip(ip)
                    .location(location)
                    .routerType(routerType)
                    .build();
            }
        };
    }

}
