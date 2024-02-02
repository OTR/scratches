package design.hexagonal.architecture.domain.spec;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.vo.RouterType;

public final class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType() == RouterType.EDGE ||
            router.getRouterType() == RouterType.CORE;
    }

}
