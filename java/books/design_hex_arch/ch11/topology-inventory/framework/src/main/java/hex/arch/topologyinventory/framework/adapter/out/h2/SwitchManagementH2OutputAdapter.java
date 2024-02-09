package hex.arch.topologyinventory.framework.adapter.out.h2;

import hex.arch.topologyinventory.application.port.out
    .SwitchManagementOutputPort;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.Id;

import hex.arch.topologyinventory.framework.adapter.out.h2.mapper.RouterH2Mapper;
import hex.arch.topologyinventory.framework.adapter.out.h2.data.SwitchData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class SwitchManagementH2OutputAdapter implements SwitchManagementOutputPort {

    private static SwitchManagementH2OutputAdapter instance;

    @PersistenceContext
    private EntityManager em;

    public SwitchManagementH2OutputAdapter() {
        setUpH2Database();
    }

    @Override
    public Switch retrieveSwitch(Id id) {
        SwitchData switchData = em.getReference(
            SwitchData.class, id.getUuid()
        );
        return RouterH2Mapper.switchDataToDomain(switchData);
    }

    private void setUpH2Database() {
        EntityManagerFactory emFactory = Persistence
            .createEntityManagerFactory("inventory");
        EntityManager em = emFactory.createEntityManager();
        this.em = em;
    }

    public static SwitchManagementH2OutputAdapter getInstance() {
        if (instance == null) {
            instance = new SwitchManagementH2OutputAdapter();
        }
        return instance;
    }

}
