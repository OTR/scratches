package hexagonal.architecture.application.use_case;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;

public interface RouterNetworkUseCase {

    Router addNetworkToRouter(RouterId routerId, Network network);

}
