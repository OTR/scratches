package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.spec.EmptyRouterSpecification;
import hex.arch.topologyinventory.domain.spec.EmptySwitchSpecification;
import hex.arch.topologyinventory.domain.spec.SameCountrySpecification;
import hex.arch.topologyinventory.domain.spec.SameIpSpecification;
import hex.arch.topologyinventory.domain.spec.shared.Specification;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public final class CoreRouter extends Router {

    private final Map<Id, Router> routers;

    @Builder
    public CoreRouter(
        Id id, Vendor vendor, Model model, IP ip, Location location,
        RouterType routerType, Map<Id, Router> routers
    ) {
        super(id, vendor, model, ip, location, routerType);
        this.routers = routers;
    }

    public Router addRouter(Router anyRouter) {
        Specification<Equipment> sameCountrySpec = new SameCountrySpecification(
    this
        );
        Specification<Equipment> sameIpSpec = new SameIpSpecification(this);

        sameCountrySpec.check(anyRouter);
        sameIpSpec.check(anyRouter);

        return this.routers.put(anyRouter.id, anyRouter);
    }

    public Router removeRouter(Router anyRouter) {
        Specification<CoreRouter> emptyRouterSpec = new EmptyRouterSpecification();
        Specification<EdgeRouter> emptySwitchSpec = new EmptySwitchSpecification();

        switch (anyRouter.routerType) {
            case CORE -> {
                CoreRouter coreRouter = (CoreRouter) anyRouter;
                emptyRouterSpec.check(coreRouter);
            }
            case EDGE -> {
                EdgeRouter edgeRouter = (EdgeRouter) anyRouter;
                emptySwitchSpec.check(edgeRouter);
            }
        }

        return this.routers.remove(anyRouter.id);
    }

}
