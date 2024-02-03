package hexagonal.architecture.framework.adapter.out.h2.mapper;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.entity.Switch;
import hexagonal.architecture.domain.vo.IP;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;
import hexagonal.architecture.domain.vo.RouterType;
import hexagonal.architecture.domain.vo.SwitchId;
import hexagonal.architecture.domain.vo.SwitchType;
import hexagonal.architecture.framework.adapter.out.h2.data.IPData;
import hexagonal.architecture.framework.adapter.out.h2.data.NetworkData;
import hexagonal.architecture.framework.adapter.out.h2.data.RouterData;
import hexagonal.architecture.framework.adapter.out.h2.data.RouterTypeData;
import hexagonal.architecture.framework.adapter.out.h2.data.SwitchData;
import hexagonal.architecture.framework.adapter.out.h2.data.SwitchTypeData;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RouterH2Mapper {

    public static Router toDomain(RouterData routerData) {
        RouterType routerType = RouterType.valueOf(routerData.getRouterType().name());
        RouterId routerId = RouterId.withId(routerData.getRouterId().toString());
        SwitchId switchId = SwitchId.withId(routerData.getNetworkSwitch().getSwitchId().toString());
        SwitchType switchType = SwitchType.valueOf(
            routerData.getNetworkSwitch().getSwitchType().toString()
        );
        IP ip = IP.fromAddress(routerData.getNetworkSwitch().getIp().getAddress());
        List<Network> networks = getNetworksFromData(
            routerData.getNetworkSwitch().getNetworks()
        );

        Switch networkSwitch = new Switch(switchId, switchType, networks, ip);

        return new Router(routerType, routerId, networkSwitch);
    }

    public static RouterData toH2(Router router) {
        RouterTypeData routerTypeData = RouterTypeData.valueOf(
            router.getRouterType().toString()
        );
        UUID routerId = router.getRouterId().getUUID();
        UUID switchId = router.getNetworkSwitch().getSwitchId().getUUID();
        SwitchTypeData switchTypeData = SwitchTypeData.valueOf(
            router.getNetworkSwitch().getSwitchType().toString()
        );
        IPData ipData = IPData.fromAddress(
            router.getNetworkSwitch().getAddress().getIPAddress()
        );
        List<NetworkData> networkDataList = getNetworksFromDomain(
            router.retrieveNetworks(), switchId
        );

        SwitchData switchData = new SwitchData(
            switchId, routerId, switchTypeData, networkDataList, ipData
        );

        return new RouterData(routerId, routerTypeData, switchData);
    }

    private static List<Network> getNetworksFromData(
        List<NetworkData> networkData
    ) {
        return networkData.stream()
                .map(network ->
                    new Network(
                        IP.fromAddress(network.getIp().getAddress()),
                        network.getName(),
                        network.getCidr()
                    )
                )
                .collect(Collectors.toList());
    }

    private static List<NetworkData> getNetworksFromDomain(
        List<Network> networks, UUID switchId
    ) {
        return networks.stream()
            .map(network ->
                new NetworkData(
                    switchId,
                    IPData.fromAddress(network.address().getIPAddress()),
                    network.name(),
                    network.cidr()
                )
            )
            .collect(Collectors.toList());
    }

}
