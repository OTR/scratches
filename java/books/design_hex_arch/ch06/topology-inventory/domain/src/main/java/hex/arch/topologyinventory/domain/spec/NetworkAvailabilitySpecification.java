package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Equipment;
import hex.arch.topologyinventory.domain.vo.Network;

public class NetworkAvailabilitySpecification implements Specification<Equipment> {
    public NetworkAvailabilitySpecification(Network network) {
    }
}
