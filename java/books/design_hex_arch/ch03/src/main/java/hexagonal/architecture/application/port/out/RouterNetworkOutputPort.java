package hexagonal.architecture.application.port.out;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.RouterId;

public interface RouterNetworkOutputPort {

    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);

}
