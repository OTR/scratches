package design.hexagonal.architecture.application.port.in;

import design.hexagonal.architecture.application.port.out.RouterNetworkOutputPort;
import design.hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.service.NetworkOperationService;
import design.hexagonal.architecture.domain.vo.Network;
import design.hexagonal.architecture.domain.vo.RouterId;

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
        Router newRouter = NetworkOperationService.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter : router;
    }

    private boolean persistNetwork(Router router) {
        return outputPort.persistRouter(router);
    }

}
