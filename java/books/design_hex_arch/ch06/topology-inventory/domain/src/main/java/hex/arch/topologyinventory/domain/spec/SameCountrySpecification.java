package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.CoreRouter;
import hex.arch.topologyinventory.domain.entity.Equipment;

public final class SameCountrySpecification extends AbstractSpecification<Equipment> {

    private final Equipment equipment;

    public SameCountrySpecification(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean isSatisfiedBy(Equipment anyEquipment) {
        if (anyEquipment instanceof CoreRouter) {
            return true;
        } else if (anyEquipment != null && this.equipment != null) {
            return this.equipment.getLocation().country().equals(
                anyEquipment.getLocation().country()
            );
        } else {
            return false;
        }
    }

    @Override
    public void check(Equipment anyEquipment) {
        if (!isSatisfiedBy(anyEquipment)) {
            throw new GenericSpecificationException(
                "The equipments should be in the same country"
            );
        }
    }

}
