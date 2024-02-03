package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Equipment;

public final class SameIpSpecification extends AbstractSpecification<Equipment> {

    private final Equipment equipment;

    public SameIpSpecification(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean isSatisfiedBy(Equipment anyEquipment) {
        return !equipment.getIp().equals(anyEquipment.getIp());
    }

    @Override
    public void check(Equipment equipment) {
        if (!isSatisfiedBy(equipment)) {
            throw new GenericSpecificationException(
                "It's not possible to attach routers with the same IP"
            );
        }
    }

}
