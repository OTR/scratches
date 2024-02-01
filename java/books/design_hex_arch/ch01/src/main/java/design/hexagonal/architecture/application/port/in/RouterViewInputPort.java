package design.hexagonal.architecture.application.port.in;

import design.hexagonal.architecture.application.use_case.RouterViewUseCase;
import design.hexagonal.architecture.application.port.out.RouterViewOutputPort;
import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.service.RouterSearchService;

import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort routerViewOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerViewOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        List<Router> routers = routerViewOutputPort.fetchRouters();
        return RouterSearchService.retrieveRouter(routers, filter);
    }

}
