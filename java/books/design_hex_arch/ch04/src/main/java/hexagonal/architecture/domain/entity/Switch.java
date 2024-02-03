package hexagonal.architecture.domain.entity;

import hexagonal.architecture.domain.vo.IP;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.SwitchId;
import hexagonal.architecture.domain.vo.SwitchType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Switch {

    private final SwitchType switchType;
    private final SwitchId switchId;
    private final List<Network> networks;
    private final IP address;

    public Switch(
        SwitchType switchType,
        SwitchId switchId,
        List<Network> networks,
        IP address
    ) {
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network) {
        List<Network> networks = new ArrayList<>(Arrays.asList(network));
        networks.add(network);

        return new Switch(
            this.switchType,
            this.switchId,
            networks,
            this.address
        );
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public SwitchType getSwitchType() {
        return switchType;
    }

    public SwitchId getSwitchId() {
        return switchId;
    }

    public IP getAddress() {
        return address;
    }

}
