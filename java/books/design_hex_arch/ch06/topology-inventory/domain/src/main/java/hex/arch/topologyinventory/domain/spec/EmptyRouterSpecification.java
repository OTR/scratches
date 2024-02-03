package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.CoreRouter;

public final class EmptyRouterSpecification extends AbstractSpecification<CoreRouter> {

    @Override
    public boolean isSatisfiedBy(CoreRouter coreRouter) {
        return coreRouter.getRouters() == null || coreRouter.getRouters().isEmpty();
    }

    @Override
    public void check(CoreRouter coreRouter) {
        if (!isSatisfiedBy(coreRouter)) {
            throw new GenericSpecificationException(
                "It isn't allowed to remove a core router with other routers attached to it"
            );
        }
    }

}
