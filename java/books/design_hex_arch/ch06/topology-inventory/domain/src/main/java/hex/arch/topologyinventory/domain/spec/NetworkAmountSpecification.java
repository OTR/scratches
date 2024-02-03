package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Equipment;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.exception.GenericSpecificationException;
import hex.arch.topologyinventory.domain.spec.shared.AbstractSpecification;

public class NetworkAmountSpecification
    extends AbstractSpecification<Equipment> {

    public static final int MAXIMUM_ALLOWED_NETWORKS = 6;

    @Override
    public boolean isSatisfiedBy(Equipment switchNetwork) {
        return ((Switch) switchNetwork).getSwitchNetworks().size()
                <= MAXIMUM_ALLOWED_NETWORKS;
    }

    @Override
    public void check(Equipment switchNetwork) throws GenericSpecificationException {
        if (!isSatisfiedBy(switchNetwork)) {
            throw new GenericSpecificationException(
                "The max number of networks is " + MAXIMUM_ALLOWED_NETWORKS
            );
        }
    }

}
