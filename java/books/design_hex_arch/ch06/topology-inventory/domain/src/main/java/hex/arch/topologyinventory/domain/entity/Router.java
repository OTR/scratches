package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import lombok.Getter;

import java.util.function.Predicate;

@Getter
public abstract sealed class Router
    extends Equipment
    permits CoreRouter, EdgeRouter {

    protected final RouterType routerType;

    public static Predicate<Router> getRouterTypePredicate(RouterType routerType) {
        return r -> r.getRouterType().equals(routerType);
    }

    public static Predicate<Equipment> getModelPredicate(Model model) {
        return r -> r.model.equals(model);
    }

    public static Predicate<Equipment> getCountryPredicate(Location location) {
        return p -> p.location.country().equals(location.country());
    }

}
