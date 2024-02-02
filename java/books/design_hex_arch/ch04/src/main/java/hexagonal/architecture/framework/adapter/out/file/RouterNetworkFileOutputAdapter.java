package hexagonal.architecture.framework.adapter.out.file;

import hexagonal.architecture.application.port.out.RouterNetworkOutputPort;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.entity.Switch;
import hexagonal.architecture.domain.vo.IP;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;
import hexagonal.architecture.domain.vo.RouterType;
import hexagonal.architecture.domain.vo.SwitchId;
import hexagonal.architecture.domain.vo.SwitchType;

import java.util.ArrayList;
import java.util.List;

public class RouterNetworkFileOutputAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkFileOutputAdapter instance;
    private List<Router> routers = new ArrayList<>();

    @Override
    public Router fetchRouterById(RouterId routerId) {
        Router retrievedRouter = null;
        for (Router router : routers) {
            if (router.getRouterId().getUUID().equals(routerId.getUUID())) {
                retrievedRouter = router;
                break;
            }
        }

        return retrievedRouter;
    }

    private void createSampleRouter() {
        RouterId routerId = RouterId.withId(
            "ca23800e-9b5a-11eb-a8b3-0242ac130003"
        );
        Network network = new Network(
            new IP("10.0.0.0"), "HR", 8
        );
        Switch networkSwitch = new Switch(
            SwitchType.LAYER3,
            SwitchId.withoutId(),
            List.of(network),
            new IP("9.0.0.9")
        );
        Router router = new Router(RouterType.EDGE, routerId, networkSwitch);
        this.routers.add(router);
    }

    @Override
    public boolean persistRouter(Router router) {
        return this.routers.add(router);
    }

    private RouterNetworkFileOutputAdapter() {
        createSampleRouter();
    }

    public static RouterNetworkFileOutputAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileOutputAdapter();
        }
        return instance;
    }

}
