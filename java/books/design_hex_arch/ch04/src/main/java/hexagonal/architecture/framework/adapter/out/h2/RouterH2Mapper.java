package hexagonal.architecture.framework.adapter.out.h2;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.IP;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.framework.adapter.out.h2.data.IPData;
import hexagonal.architecture.framework.adapter.out.h2.data.NetworkData;
import hexagonal.architecture.framework.adapter.out.h2.data.RouterData;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RouterH2Mapper {

    public static Router toDomain(RouterData routerData) {

        return new Router(routerType, routerId, networkSwitch);
    }

    public static RouterData toH2(Router router) {

        return new RouterData(routerId, routerDataType, switchData);
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
