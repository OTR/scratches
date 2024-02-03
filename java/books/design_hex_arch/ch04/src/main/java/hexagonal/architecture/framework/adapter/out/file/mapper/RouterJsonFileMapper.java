package hexagonal.architecture.framework.adapter.out.file.mapper;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.IP;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.framework.adapter.out.file.json.IPJson;
import hexagonal.architecture.framework.adapter.out.file.json.NetworkJson;
import hexagonal.architecture.framework.adapter.out.file.json.RouterJson;

import java.util.List;
import java.util.stream.Collectors;

public class RouterJsonFileMapper {

    public static Router toDomain(RouterJson routerJson) {

        return new Router(routerType, routerId, networkSwitch);
    }

    public static RouterJson toJson(Router router) {
        return new RouterJson(
                routerId,
                routerType,
                switchJson
        );
    }

    private static List<Network> getNetworksFromJson(
        List<NetworkJson> networkJson
    ) {
        return networkJson.stream()
                .map(json ->
                    new Network(
                            IP.fromAddress(json.getIp().getAddress()),
                            json.getNetworkName(),
                            json.getCidr()
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
                    IPJson.fromAddress(network.getAddress().getIPAddress())
                )
            )
            .collect(Collectors.toList());
    }

}
