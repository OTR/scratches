package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import lombok.Getter;

import java.util.function.Predicate;

@Getter
public abstract sealed class Router
    extends Equipment
    permits CoreRouter, EdgeRouter {

    protected final RouterType routerType;

    public static Predicate<Equipment> getRouterTypePredicate(RouterType routerType) {
        return r -> ((Router)r).getRouterType().equals(routerType);
    }

    public static Predicate<Equipment> getModelPredicate(Model model) {
        return r -> r.getModel().equals(model);
    }

    public static Predicate<Equipment> getCountryPredicate(Location location) {
        return p -> p.location.country().equals(location.country());
    }

    public Router(
        Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType
    ) {
        super(id, vendor, model, ip, location);
        this.routerType = routerType;
    }

}
