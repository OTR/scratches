package hexagonal.architecture.framework.adapter.in;

import hexagonal.architecture.application.port.in.RouterNetworkInputPort;
import hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;
import hexagonal.architecture.framework.adapter.out.RouterNetworkFileOutputAdapter;

public class RouterNetworkCliInputAdapter {

    private RouterNetworkUseCase routerNetworkUseCase;

    public RouterNetworkCliInputAdapter() {
        setAdapters();
    }

    public Router addNetwork(RouterId routerId, Network network) {
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    private void setAdapters() {
        this.routerNetworkUseCase = new RouterNetworkInputPort(
                RouterNetworkFileOutputAdapter.getInstance()
        );
    }

}
