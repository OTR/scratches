package hex.arch.topologyinventory.framework.adapter.out.h2;

import hex.arch.topologyinventory.application.port.out.SwitchManagementOutputPort;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.Id;

import hex.arch.topologyinventory.framework.adapter.out.h2.mapper.RouterH2Mapper;
import hex.arch.topologyinventory.framework.adapter.out.h2.data.SwitchData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class SwitchManagementH2OutputAdapter implements SwitchManagementOutputPort {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Switch retrieveSwitch(Id id) {
        SwitchData switchData = em.getReference(
            SwitchData.class, id.getId()
        );
        return RouterH2Mapper.switchDataToDomain(switchData);
    }

}
