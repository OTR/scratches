package hexagonal.architecture.framework.adapter.out;

import hexagonal.architecture.application.port.out.RouterNetworkOutputPort;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.RouterId;

public class RouterNetworkH2OutputAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkH2OutputAdapter instance;

    @Override
    public Router fetchRouterById(RouterId routerId) {
        return null;
    }

    @Override
    public boolean persistRouter(Router router) {
        return false;
    }

    public static RouterNetworkH2OutputAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkH2OutputAdapter();
        }
        return instance;
    }

}
