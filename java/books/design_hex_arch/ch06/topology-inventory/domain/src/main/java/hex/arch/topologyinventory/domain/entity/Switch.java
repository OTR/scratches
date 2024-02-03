package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.spec.CIDRSpecification;
import hex.arch.topologyinventory.domain.spec.NetworkAmountSpecification;
import hex.arch.topologyinventory.domain.spec.NetworkAvailabilitySpecification;
import hex.arch.topologyinventory.domain.spec.Specification;
import hex.arch.topologyinventory.domain.vo.Network;
import hex.arch.topologyinventory.domain.vo.SwitchType;

import java.util.List;
import java.util.function.Predicate;

public final class Switch extends Equipment {

    private final SwitchType switchType;
    private final List<Network> switchNetworks;

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
