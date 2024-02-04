package hex.arch.topologyinventory.application.use_case;

import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.SwitchType;
import hex.arch.topologyinventory.domain.vo.Vendor;

public interface SwitchManagementUseCase {

    Switch createSwitch(
        Vendor vendor, Model model, IP ip,
        Location location, SwitchType switchType
    );

    EdgeRouter addSwitchToEdgeRouter(
        Switch networkSwitch, EdgeRouter edgeRouter
    );

    EdgeRouter removeSwitchFromEdgeRouter(
        Switch networkSwitch, EdgeRouter edgeRouter
    );

}
