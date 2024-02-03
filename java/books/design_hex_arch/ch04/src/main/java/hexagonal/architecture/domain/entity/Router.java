package hexagonal.architecture.domain.entity;

import hexagonal.architecture.domain.vo.IP;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;
import hexagonal.architecture.domain.vo.RouterType;

import java.util.List;

public class Router {

    private RouterType routerType;
    private RouterId routerId;
    private Switch networkSwitch;

    public Router() {}

    public Router(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public Router(RouterType routerType, RouterId routerId, Switch networkSwitch) {
        this.routerType = routerType;
        this.routerId = routerId;
        this.networkSwitch = networkSwitch;
    }

    public boolean isType(RouterType type) {
        return this.routerType == type;
    }

    public void addNetworkToSwitch(Network network) {
        this.networkSwitch = this.networkSwitch.addNetwork(network);
    }

    public Network createNetwork(IP address, String name, int cidr) {
        return new Network(address, name, cidr);
    }

    public List<Network> retrieveNetworks() {
        return this.networkSwitch.getNetworks();
    }

    public Switch getNetworkSwitch() {
        return this.networkSwitch;
    }

    public RouterType getRouterType() {
        return this.routerType;
    }

    public RouterId getRouterId() {
        return this.routerId;
    }

    @Override
    public String toString() {
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                "}";
    }

}
