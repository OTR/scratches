package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.spec.EmptyNetworkSpecification;
import hex.arch.topologyinventory.domain.spec.SameCountrySpecification;
import hex.arch.topologyinventory.domain.spec.SameIpSpecification;
import hex.arch.topologyinventory.domain.spec.Specification;
import hex.arch.topologyinventory.domain.vo.Id;

import java.util.Map;

public final class EdgeRouter extends Router {

    private final Map<Id, Switch> switches;

    public void addSwitch(Switch anySwitch) {
        Specification<Equipment> sameCountrySpec = new SameCountrySpecification(this);
        Specification<Equipment> sameIpSpec = new SameIpSpecification(this);

        sameCountrySpec.check(anySwitch);
        sameIpSpec.check(anySwitch);

        this.switches.put(anySwitch.id, anySwitch);

    }

    public Switch removeSwitch(Switch anySwitch) {
        Specification<Equipment> emptyNetworkSpec = new EmptyNetworkSpecification();
        emptyNetworkSpec.check(anySwitch);
        return this.switches.remove(anySwitch.id);
    }

}
