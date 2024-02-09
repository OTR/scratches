package hex.arch.topologyinventory.application.port.in;

import hex.arch.topologyinventory.application.port.out.
    SwitchManagementOutputPort;
import hex.arch.topologyinventory.application.use_case.
    SwitchManagementUseCase;

import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.SwitchType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SwitchManagementInputPort implements SwitchManagementUseCase {

    @Inject
    private SwitchManagementOutputPort outputPort;

    public SwitchManagementInputPort() {}

    @Override
    public Switch createSwitch(
        Vendor vendor, Model model, IP ip,
        Location location, SwitchType switchType
    ) {
        return Switch.builder()
            .switchId(Id.withoutId())
            .vendor(vendor)
            .model(model)
            .ip(ip)
            .location(location)
            .switchType(switchType)
            .build();
    }

    public Switch retrieveSwitch(Id id) {
        return outputPort.retrieveSwitch(id);
    }

    @Override
    public EdgeRouter addSwitchToEdgeRouter(
        Switch networkSwitch, EdgeRouter edgeRouter
    ) {
        networkSwitch.setRouterId(edgeRouter.getId());
        edgeRouter.addSwitch(networkSwitch);
        return edgeRouter;
    }

    @Override
    public EdgeRouter removeSwitchFromEdgeRouter(
        Switch networkSwitch, EdgeRouter edgeRouter
    ) {
        edgeRouter.removeSwitch(networkSwitch);
        return edgeRouter;
    }

}
