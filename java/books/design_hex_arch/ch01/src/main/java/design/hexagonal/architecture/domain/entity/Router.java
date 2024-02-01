package design.hexagonal.architecture.domain.entity;

import design.hexagonal.architecture.domain.vo.RouterId;
import design.hexagonal.architecture.domain.vo.RouterType;

public class Router {

    private final RouterId id;
    private final RouterType type;

    public Router(RouterType type, RouterId id) {
        this.type = type;
        this.id = id;
    }

    public static List<Router> checkRouter(
        RouterType type, List<Router> routers
    ) {
        var routersList = new ArrayList<Router>();
        routers.forEach(router -> {
            if (router.type.equals(type)) {
                routersList.add(router);
            }
        });

        return routersList;
    }

}
