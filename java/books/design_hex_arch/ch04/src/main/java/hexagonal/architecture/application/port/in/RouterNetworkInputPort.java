package hexagonal.architecture.application.port.in;

import hexagonal.architecture.application.port.out.RouterNetworkOutputPort;
import hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.service.NetworkOperationService;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;

public class RouterNetworkInputPort implements RouterNetworkUseCase {

    private final RouterNetworkOutputPort outputPort;

    public RouterNetworkInputPort(RouterNetworkOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        Router router = fetchRouter(routerId);
        return createNetwork(router, network);
    }

    private Router fetchRouter(RouterId routerId) {
        return outputPort.fetchRouterById(routerId);
    }

    private Router createNetwork(Router router, Network network) {
        Router newRouter = NetworkOperationService.createNewNetwork(
            router, network
        );
        return persistNetwork(router) ? newRouter : router;
    }

    private boolean persistNetwork(Router router) {
        return outputPort.persistRouter(router);
    }

}
