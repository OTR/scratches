package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Router;

public class EmptySwitchSpecification implements Specification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return false;
    }

    @Override
    public void check(Router router) {

    }
}
