package design.hexagonal.architecture.domain.entity;

import design.hexagonal.architecture.domain.vo.RouterId;
import design.hexagonal.architecture.domain.vo.RouterType;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Router {

    private final RouterId routerId;
    private final RouterType routerType;

    public Router(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public static Predicate<Router> filterRouterByType(RouterType routerType) {
        return routerType.equals(RouterType.CORE) ? isCore() : isEdge();
    }

    private static Predicate<Router> isCore() {
        return p -> p.getRouterType() == RouterType.CORE;
    }

    private static Predicate<Router> isEdge() {
        return p -> p.getRouterType() == RouterType.EDGE;
    }

    public static List<Router> retrieveRouter(
            List<Router> routers,
            Predicate<Router> predicate
    ) {
        return routers.stream()
                .filter(predicate)
                .collect(Collectors.<Router>toList());
    }

    private RouterType getRouterType() {
        return this.routerType;
    }

    @Override
    public String toString() {
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                "}";
    }

}
