package design.hexagonal.architecture.application.port.in;

import design.hexagonal.architecture.application.use_case.RouterViewUseCase;
import design.hexagonal.architecture.domain.entity.Router;

public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort RouterViewOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerViewOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        List<Router> routers = routerViewOutputPort.fetchRouters();

        return Router.retrieveRouter(routers, filter);
    }

}
