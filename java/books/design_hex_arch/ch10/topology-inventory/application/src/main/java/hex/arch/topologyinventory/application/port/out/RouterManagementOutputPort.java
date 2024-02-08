package hex.arch.topologyinventory.application.port.out;

import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.vo.Id;

public interface RouterManagementOutputPort {

    Router persistRouter(Router router);

    Router retrieveRouter(Id id);

    Router removeRouter(Id id);

}
