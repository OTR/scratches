package hex.arch.topologyinventory.application;

import hex.arch.topologyinventory.application.use_case.RouterManagementUseCase;
import hex.arch.topologyinventory.domain.entity.CoreRouter;
import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import io.cucumber.java.en.Given;

import static org.junit.Assert.assertNotNull;

public class RouterAdd {

    private RouterManagementUseCase routerManagementUseCase;
    private Location locationA;

    @Given("I have an edge router")
    public void assert_edge_router_exists() {
        EdgeRouter edgeRouter = (EdgeRouter) this
            .routerManagementUseCase
            .createRouter(
                Vendor.HP,
                Model.XYZ0004,
                IP.fromAddress("20.0.0.1"),
                this.locationA,
                RouterType.EDGE
            );

        assertNotNull(edgeRouter);
    }

    @Given("I have a core router")
    public void assert_core_router_exists() {
        CoreRouter coreRouter = (CoreRouter) this
            .routerManagementUseCase
            .createRouter(
                Vendor.CISCO,
                Model.XYZ0001,
                IP.fromAddress("30.0.0.1"),
                this.locationA,
                RouterType.CORE
            );

        assertNotNull(coreRouter);
    }

}
