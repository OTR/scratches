package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.spec.EmptyNetworkSpecification;
import hex.arch.topologyinventory.domain.spec.SameCountrySpecification;
import hex.arch.topologyinventory.domain.spec.SameIpSpecification;
import hex.arch.topologyinventory.domain.spec.shared.Specification;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public final class EdgeRouter extends Router {

    private final Map<Id, Switch> switches;

    public EdgeRouter(
            Id id, Vendor vendor, Model model, IP ip, Location location,
            RouterType routerType, Map<Id, Switch> switches
    ) {
        super(id, vendor, model, ip, location, routerType);
        this.switches = switches;
    }

    public void addSwitch(Switch anySwitch) {
        Specification<Equipment> sameCountrySpec = new SameCountrySpecification(
        this
        );
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
