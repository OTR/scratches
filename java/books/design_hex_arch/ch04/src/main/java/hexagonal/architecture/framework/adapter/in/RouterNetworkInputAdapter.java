package hexagonal.architecture.framework.adapter.in;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import hexagonal.architecture.domain.vo.IP;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;

import java.util.Map;

public abstract class RouterNetworkInputAdapter {

    protected Router router;
    protected RouterNetworkUseCase routerNetworkUseCase;

    public Router addNetworkToRouter(Map<String, String> params) {
        RouterId routerId = RouterId.withId(params.get("routerId"));
        Network network = new Network(
            IP.fromAddress(params.get("address")),
            params.get("name"),
            Integer.valueOf(params.get("cidr"))
        );

        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    public abstract Router processRequest(Object requestParams);

}
