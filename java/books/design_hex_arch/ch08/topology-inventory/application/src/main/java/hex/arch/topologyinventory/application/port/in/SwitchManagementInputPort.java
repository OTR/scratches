package hex.arch.topologyinventory.application.port.in;

import hex.arch.topologyinventory.application.port.out.SwitchManagementOutputPort;
import hex.arch.topologyinventory.application.use_case.SwitchManagementUseCase;

import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.SwitchType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SwitchManagementInputPort implements SwitchManagementUseCase {

    private SwitchManagementOutputPort outputPort;

    public SwitchManagementInputPort(SwitchManagementOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public Switch createSwitch(
        Vendor vendor, Model model, IP ip,
        Location location, SwitchType switchType
    ) {
        return Switch.builder()
            .routerId(Id.withoutId())
            .vendor(vendor)
            .model(model)
            .ip(ip)
            .location(location)
            .switchType(switchType)
            .build();
    }

    @Override
    public EdgeRouter addSwitchToEdgeRouter(
        Switch networkSwitch, EdgeRouter edgeRouter
    ) {
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

    @Override
    public Switch retrieveSwitch(Id switchId) {
        return outputPort.retrieveSwitch(switchId);
    }

}
