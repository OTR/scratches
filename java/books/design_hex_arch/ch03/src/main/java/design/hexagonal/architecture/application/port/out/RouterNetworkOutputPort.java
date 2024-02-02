package design.hexagonal.architecture.application.port.out;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.vo.RouterId;

public interface RouterNetworkOutputPort {

    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);

}
