package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Equipment;

public class SameIpSpecification extends AbstractSpecification<Equipment> {

    @Override
    public boolean isSatisfiedBy(Equipment equipment) {
        return false;
    }

    @Override
    public void check(Equipment equipment) {

    }
}
