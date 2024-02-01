package design.hexagonal.architecture.domain.entity;

import design.hexagonal.architecture.domain.exception.NotImplementedYet;
import design.hexagonal.architecture.domain.vo.RouterId;
import design.hexagonal.architecture.domain.vo.RouterType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    public static List<Router> retrieveRouter(
            List<Router> routers,
            Predicate<Router> filter
    ) {
        throw new NotImplementedYet();
    }
}
