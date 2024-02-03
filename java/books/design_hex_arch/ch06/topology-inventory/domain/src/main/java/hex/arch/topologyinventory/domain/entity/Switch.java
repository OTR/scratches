package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.spec.CIDRSpecification;
import hex.arch.topologyinventory.domain.spec.NetworkAmountSpecification;
import hex.arch.topologyinventory.domain.spec.NetworkAvailabilitySpecification;
import hex.arch.topologyinventory.domain.spec.Specification;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.Network;
import hex.arch.topologyinventory.domain.vo.Protocol;
import hex.arch.topologyinventory.domain.vo.SwitchType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.function.Predicate;

@Getter
public final class Switch extends Equipment {

    private final SwitchType switchType;
    private final List<Network> switchNetworks;

    @Builder
    public Switch(
        Id id, Vendor vendor, Model model, IP ip, Location location,
        SwitchType switchType, List<Network> switchNetworks
    ) {
        super(id, vendor, model, ip, location);
        this.switchType = switchType;
        this.switchNetworks = switchNetworks;
    }

    public static Predicate<Network> getNetworkProtocolPredicate(Protocol protocol) {
        return s -> s.getNetworkAddress().getProtocol().equals(protocol);
    }

    public static Predicate<Switch> getSwitchTypePredicate(SwitchType switchType) {
        return s -> s.switchType.equals(switchType);
    }

    public boolean addNetworkToSwitch(Network network) {
        Specification<Equipment> availabilitySpec = new NetworkAvailabilitySpecification(network);
        Specification<Integer> cidrSpec = new CIDRSpecification();
        Specification<Equipment> amountSpec = new NetworkAmountSpecification();

        cidrSpec.check(network.getNetworkCidr());
        availabilitySpec.check(this);
        amountSpec.check(this);

        return this.switchNetworks.add(network);
    }

    public boolean removeNetworkFromSwitch(Network network) {
        return this.switchNetworks.remove(network);
    }

}
