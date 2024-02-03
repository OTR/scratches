package hexagonal.architecture.application.port.in;

import hexagonal.architecture.application.port.out.RouterViewOutputPort;
import hexagonal.architecture.application.use_case.RouterViewUseCase;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.service.RouterSearchService;
import hexagonal.architecture.domain.vo.RouterType;

import java.util.List;

public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort outputPort;
    private Router router;

    public RouterViewInputPort(RouterViewOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public List<Router> getRelatedRouters(
        RelatedRoutersCommand relatedRoutersCommand
    ) {
        RouterType type = relatedRoutersCommand.getType();
        List<Router> routers = outputPort.fetchRelatedRouters();
        return this.fetchRelatedEdgeRouters(routers, type);
    }

    private List<Router> fetchRelatedEdgeRouters(
        List<Router> routers,
        RouterType type
    ) {
        return RouterSearchService.getRouters(routers, type);
    }

}
