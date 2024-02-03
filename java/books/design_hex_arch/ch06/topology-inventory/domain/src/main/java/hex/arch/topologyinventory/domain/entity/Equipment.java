package hex.arch.topologyinventory.domain.entity;

import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.Vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Predicate;

@AllArgsConstructor
@Getter
public abstract sealed class Equipment permits Router, Switch {

    protected Id id;
    protected Vendor vendor;
    protected Model model;
    protected IP ip;
    protected Location location;

    public static Predicate<Equipment> getVendorPredicate(Vendor vendor) {
        return r -> r.getVendor().equals(vendor);
    }

}
