package hex.arch.topologyinventory.framework.adapter.out.h2;

import hex.arch.topologyinventory.application.port.out.RouterManagementOutputPort;
import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.framework.adapter.out.h2.data.RouterData;
import hex.arch.topologyinventory.framework.adapter.out.h2.mapper.RouterH2Mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class RouterManagementH2OutputAdapter implements RouterManagementOutputPort {

    private static RouterManagementH2OutputAdapter instance;

    @PersistenceContext
    private EntityManager em;

    public RouterManagementH2OutputAdapter() {
        setUpH2Database();
    }

    @Override
    public Router persistRouter(Router router) {
        RouterData routerData = RouterH2Mapper.routerDomainToData(router);
        em.persist(routerData);
        return router;
    }

    @Override
    public Router retrieveRouter(Id id) {
        RouterData routerData = em.getReference(
            RouterData.class, id.getId()
        );
        return RouterH2Mapper.routerDataToDomain(routerData);
    }

    @Override
    public Router removeRouter(Id id) {
        RouterData routerData = em.getReference(
            RouterData.class, id.getId()
        );
        em.remove(routerData);
        return null;
    }

    private void setUpH2Database() {

    }

    public static RouterManagementOutputPort getInstance() {
        if (instance == null) {
            instance = new RouterManagementH2OutputAdapter();
        }
        return instance;
    }

}
