package design.hexagonal.architecture.application.use_case;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.vo.Network;
import design.hexagonal.architecture.domain.vo.RouterId;

public interface RouterNetworkUseCase {

    Router addNetworkToRouter(RouterId routerId, Network network);

}
