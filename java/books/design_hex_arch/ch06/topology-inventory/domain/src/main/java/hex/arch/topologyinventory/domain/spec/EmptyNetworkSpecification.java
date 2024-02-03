package hex.arch.topologyinventory.domain.spec;

import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.exception.GenericSpecificationException;
import hex.arch.topologyinventory.domain.spec.shared.AbstractSpecification;

public final class EmptyNetworkSpecification extends AbstractSpecification<Switch> {

    @Override
    public boolean isSatisfiedBy(Switch switchNetwork) {
        return switchNetwork.getSwitchNetworks() == null
                || switchNetwork.getSwitchNetworks().isEmpty();
    }

    @Override
    public void check(Switch switchNetwork) throws GenericSpecificationException {
        if (!isSatisfiedBy(switchNetwork)) {
            throw new GenericSpecificationException(
                "It's not possible to remove a switch with networks attached to it"
            );
        }
    }

}
