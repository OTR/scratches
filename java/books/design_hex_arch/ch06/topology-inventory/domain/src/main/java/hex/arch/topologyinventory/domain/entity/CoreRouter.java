package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.spec.EmptyRouterSpecification;
import hex.arch.topologyinventory.domain.spec.EmptySwitchSpecification;
import hex.arch.topologyinventory.domain.spec.SameCountrySpecification;
import hex.arch.topologyinventory.domain.spec.SameIpSpecification;
import hex.arch.topologyinventory.domain.spec.Specification;
import hex.arch.topologyinventory.domain.vo.Id;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
public final class CoreRouter extends Router {

    @Getter
    private final Map<Id, Router> routers;

    public CoreRouter(Map<Id, Router> routers) {
        this.routers = routers;
    }

    public Router addRouter(Router anyRouter) {
        Specification<Equipment> sameCountrySpec = new SameCountrySpecification(this);
        Specification<Equipment> sameIpSpec = new SameIpSpecification(this);

        sameCountrySpec.check(anyRouter);
        sameIpSpec.check(anyRouter);

        return this.routers.put(anyRouter.id, anyRouter);
    }

    public Router removeRouter(Router anyRouter) {
        Specification<Equipment> emptyRouterSpec = new EmptyRouterSpecification();
        Specification<Equipment> emptySwitchSpec = new EmptySwitchSpecification();

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
