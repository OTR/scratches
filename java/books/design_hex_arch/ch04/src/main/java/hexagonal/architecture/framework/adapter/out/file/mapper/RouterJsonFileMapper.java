package hexagonal.architecture.framework.adapter.out.file.mapper;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.entity.Switch;
import hexagonal.architecture.domain.vo.IP;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;
import hexagonal.architecture.domain.vo.RouterType;
import hexagonal.architecture.domain.vo.SwitchId;
import hexagonal.architecture.domain.vo.SwitchType;
import hexagonal.architecture.framework.adapter.out.file.json.IPJson;
import hexagonal.architecture.framework.adapter.out.file.json.NetworkJson;
import hexagonal.architecture.framework.adapter.out.file.json.RouterJson;
import hexagonal.architecture.framework.adapter.out.file.json.RouterTypeJson;
import hexagonal.architecture.framework.adapter.out.file.json.SwitchJson;
import hexagonal.architecture.framework.adapter.out.file.json.SwitchTypeJson;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RouterJsonFileMapper {

    public static Router toDomain(RouterJson routerJson) {
        RouterId routerId = RouterId.withId(routerJson.getRouterId().toString());
        RouterType routerType = RouterType.valueOf(routerJson.getRouterType().name());
        SwitchId switchId = SwitchId.withId(routerJson.getNetworkSwitch().getId().toString());
        SwitchType switchType = SwitchType.valueOf(routerJson.getNetworkSwitch().getSwitchType().toString());
        IP ip = IP.fromAddress(routerJson.getNetworkSwitch().getIp().getAddress());
        List<Network> networks = getNetworksFromJson(routerJson.getNetworkSwitch().getNetworks());

        Switch networkSwitch = new Switch(switchId, switchType, networks, ip);

        return new Router(routerType, routerId, networkSwitch);
    }

    public static RouterJson toJson(Router router) {
        UUID routerId = router.getRouterId().getUUID();
        RouterTypeJson routerTypeJson = RouterTypeJson.valueOf(router.getRouterType().toString());
        UUID switchId = router.getNetworkSwitch().getSwitchId().getUUID();
        SwitchTypeJson switchTypeJson = SwitchTypeJson.valueOf(
            router.getNetworkSwitch().getSwitchType().toString()
        );
        IPJson ipJson = IPJson.fromAddress(router.getNetworkSwitch().getAddress().getIPAddress());
        List<NetworkJson> networkJsons = getNetworksFromDomain(router.retrieveNetworks());

        SwitchJson switchJson = new SwitchJson(switchId, ipJson, switchTypeJson, networkJsons);

        return new RouterJson(routerId, routerTypeJson, switchJson);
    }

    private static List<Network> getNetworksFromJson(
        List<NetworkJson> networkJsons
    ) {
        return networkJsons.stream()
                .map(json ->
                    new Network(
                            IP.fromAddress(json.getIp().getAddress()),
                            json.getNetworkName(),
                            Integer.valueOf(json.getCidr())
                    )
                )
                .collect(Collectors.toList());
    }

    private static List<NetworkJson> getNetworksFromDomain(
        List<Network> networks
    ) {
        return networks.stream()
            .map(network ->
                new NetworkJson(
                    IPJson.fromAddress(network.address().getIPAddress()),
                    network.name(),
                    String.valueOf(network.cidr())
                )
            )
            .collect(Collectors.toList());
    }

}
