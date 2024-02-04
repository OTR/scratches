package hex.arch.topologyinventory.framework.adapter.out.h2;

import hex.arch.topologyinventory.application.port.out.RouterManagementOutputPort;
import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.vo.Id;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class RouterManagementH2OutputAdapter implements RouterManagementOutputPort {

    private static RouterManagementH2OutputAdapter instance;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Router persistRouter(Router router) {
        return null;
    }

    @Override
    public Router retrieveRouter(Id id) {
        return null;
    }

    @Override
    public Router removeRouter(Id id) {
        return null;
    }
}
