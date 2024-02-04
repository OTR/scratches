package hex.arch.topologyinventory.application.port.out;

import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.entity.Switch;

public interface SwitchManagementOutputPort {

    Switch retrieveSwitch(Id id);

}
