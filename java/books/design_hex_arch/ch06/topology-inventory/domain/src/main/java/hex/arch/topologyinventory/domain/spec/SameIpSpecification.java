package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Router;

public class SameIpSpecification extends AbstractSpecification<Router> {
    @Override
    public boolean isSatisfiedBy(Router router) {
        return false;
    }

    @Override
    public void check(Router router) {

    }
}
