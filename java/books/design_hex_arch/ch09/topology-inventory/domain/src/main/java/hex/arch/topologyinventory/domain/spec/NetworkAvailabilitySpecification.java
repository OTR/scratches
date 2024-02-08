package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Equipment;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.exception.GenericSpecificationException;
import hex.arch.topologyinventory.domain.spec.shared.AbstractSpecification;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Network;

public class NetworkAvailabilitySpecification extends AbstractSpecification<Equipment> {

    private IP address;
    private String name;
    private int cidr;

    public NetworkAvailabilitySpecification(Network network) {
        this.address = network.getNetworkAddress();
        this.name = network.getNetworkName();
        this.cidr = network.getNetworkCidr();
    }

    @Override
    public boolean isSatisfiedBy(Equipment switchNetwork) {
        return switchNetwork != null
            && isNetworkAvailable(switchNetwork);
    }

    @Override
    public void check(Equipment switchNetwork) throws GenericSpecificationException {
        if (!isSatisfiedBy(switchNetwork)) {
            throw new GenericSpecificationException("This network already exists");
        }
    }

    private boolean isNetworkAvailable(Equipment switchNetwork) {
        boolean availability = true;

        for (Network network : ((Switch) switchNetwork).getSwitchNetworks()) {
            if (
                network.getNetworkAddress().equals(this.address)
                    && network.getNetworkName().equals(this.name)
                    && network.getNetworkCidr() == this.cidr
            ) {
                availability = false;
                break;
            }
        }

        return availability;
    }

}
