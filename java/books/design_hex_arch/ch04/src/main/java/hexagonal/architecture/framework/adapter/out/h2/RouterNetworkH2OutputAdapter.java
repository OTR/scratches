package hexagonal.architecture.framework.adapter.out.h2;

import hexagonal.architecture.application.port.out.RouterNetworkOutputPort;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.RouterId;

import hexagonal.architecture.framework.adapter.out.h2.data.RouterData;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

public class RouterNetworkH2OutputAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkH2OutputAdapter instance;

    @PersistenceContext
    private EntityManager em;

    private RouterNetworkH2OutputAdapter() {
        setUpH2Database();
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        RouterData routerData = em.getReference(
            RouterData.class, routerId.getUUID()
        );
        return RouterH2Mapper.toDomain(routerData);
    }

    @Override
    public boolean persistRouter(Router router) {
        RouterData routerData = RouterH2Mapper.toH2(router);
        em.persist(routerData);
        return true;
    }

    private void setUpH2Database() {
        EntityManagerFactory emFactory = Persistence
            .createEntityManagerFactory("inventory");
        EntityManager em = emFactory.createEntityManager();
        this.em = em;
    }

    public static RouterNetworkH2OutputAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkH2OutputAdapter();
        }
        return instance;
    }

}
