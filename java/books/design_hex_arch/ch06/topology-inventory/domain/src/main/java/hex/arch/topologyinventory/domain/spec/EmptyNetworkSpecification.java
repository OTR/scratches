package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Equipment;

public final class EmptyNetworkSpecification implements Specification<Equipment> {

    @Override
    public boolean isSatisfiedBy(Equipment equipment) {
        return false;
    }

    @Override
    public void check(Equipment equipment) {

    }

}
