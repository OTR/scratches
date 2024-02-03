package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.spec.EmptyRouterSpecification;
import hex.arch.topologyinventory.domain.spec.EmptySwitchSpecification;
import hex.arch.topologyinventory.domain.spec.SameCountrySpecification;
import hex.arch.topologyinventory.domain.spec.SameIpSpecification;
import hex.arch.topologyinventory.domain.spec.Specification;

public final class CoreRouter extends Router {

    public Router addRouter(Router anyRouter) {
        Specification<Equipment> sameCountrySpec = new SameCountrySpecification();
        Specification<Equipment> sameIpSpec = new SameIpSpecification();

        sameCountrySpec.check(anyRouter);
        sameIpSpec.check(anyRouter);

        return this.routers.put(anyRouter.id, anyRouter);
    }

    public Router removeRouter(Router anyRouter) {
        Specification<Router> emptyRouterSpec = new EmptyRouterSpecification();
        Specification<Router> emptySwitchSpec = new EmptySwitchSpecification();

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
