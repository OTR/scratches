package hex.arch.topologyinventory.framework;

import hex.arch.topologyinventory.application.port.out.RouterManagementOutputPort;
import hex.arch.topologyinventory.application.port.out.SwitchManagementOutputPort;
import hex.arch.topologyinventory.application.use_case.NetworkManagementUseCase;
import hex.arch.topologyinventory.application.use_case.RouterManagementUseCase;
import hex.arch.topologyinventory.application.use_case.SwitchManagementUseCase;
import hex.arch.topologyinventory.domain.entity.CoreRouter;
import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.entity.Router;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.IP;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Model;
import hex.arch.topologyinventory.domain.vo.Network;
import hex.arch.topologyinventory.domain.vo.RouterType;
import hex.arch.topologyinventory.domain.vo.SwitchType;
import hex.arch.topologyinventory.domain.vo.Vendor;

import hex.arch.topologyinventory.framework.adapter.in
    .NetworkManagementGenericInputAdapter;
import hex.arch.topologyinventory.framework.adapter.in
    .RouterManagementGenericInputAdapter;
import hex.arch.topologyinventory.framework.adapter.in
    .SwitchManagementGenericInputAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class FrameworkTestData {

    protected RouterManagementGenericInputAdapter routerIA;
    protected SwitchManagementGenericInputAdapter switchIA;
    protected NetworkManagementGenericInputAdapter networkIA;

    protected List<Router> routers = new ArrayList<>();
    protected List<Switch> switches = new ArrayList<>();
    protected List<Network> networks = new ArrayList<>();

    protected Map<Id, Router> routersOfCoreRouter = new HashMap<>();
    protected Map<Id, Switch> switchesOfEdgeRouter = new HashMap<>();

    protected Network network;
    protected Switch networkSwitch;

    protected CoreRouter coreRouter;
    protected CoreRouter newCoreRouter;

    protected EdgeRouter edgeRouter;
    protected EdgeRouter newEdgeRouter;

    protected Location locationA;
    protected Location locationB;

    protected void loadPortsAndUseCases() {

        // Router
        ServiceLoader<RouterManagementUseCase> routerUCLoad = ServiceLoader.load(RouterManagementUseCase.class);
        ServiceLoader<RouterManagementOutputPort> routerOPLoad  = ServiceLoader.load(RouterManagementOutputPort.class);

        RouterManagementUseCase routerUC = routerUCLoad.findFirst().get();
        RouterManagementOutputPort routerOP = routerOPLoad.findFirst().get();

        routerUC.setOutputPort(routerOP);
        this.routerIA = new RouterManagementGenericInputAdapter(routerUC);


        // Switch
        ServiceLoader<SwitchManagementUseCase> switchUCLoad = ServiceLoader.load(SwitchManagementUseCase.class);
        ServiceLoader<SwitchManagementOutputPort>  switchOPLoad = ServiceLoader.load(SwitchManagementOutputPort.class);

        SwitchManagementOutputPort switchOP = switchOPLoad.findFirst().get();
        SwitchManagementUseCase switchUC = switchUCLoad .findFirst().get();

        switchUC.setOutputPort(switchOP);
        this.switchIA = new SwitchManagementGenericInputAdapter(routerUC, switchUC);

        // Network
        ServiceLoader<NetworkManagementUseCase> networkUCLoad = ServiceLoader.load(NetworkManagementUseCase.class);
        NetworkManagementUseCase networkUC = networkUCLoad.findFirst().get();
        networkUC.setOutputPort(routerOP);
        this.networkIA = new NetworkManagementGenericInputAdapter(switchUC, networkUC);

    }

    public void loadData() {
        this.locationA = Location.builder().
            address("Amos Ln").
            city("Tully").
            state("NY").
            country("United States").
            zipCode(13159).
            latitude(42.797310F).
            longitude(-76.130750F).
            build();
        this.locationB = Location.builder().
            address("Av Republica Argentina 3109").
            city("Curitiba").
            state("PR").
            country("Italy").
            zipCode(80610260).
            latitude(10F).
            longitude(-10F).
            build();
        this.network = Network.builder().
            networkAddress(IP.fromAddress("20.0.0.0")).
            networkName("TestNetwork").
            networkCidr(8).
            build();
        this.networks.add(network);
        this.networkSwitch = Switch.builder().
            switchId(Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).
            vendor(Vendor.CISCO).
            model(Model.XYZ0004).
            ip(IP.fromAddress("20.0.0.100")).
            location(locationA).
            switchType(SwitchType.LAYER3).
            switchNetworks(networks).
            build();
        this.switchesOfEdgeRouter.put(networkSwitch.getId(), networkSwitch);
        this.edgeRouter = EdgeRouter.builder().
            id(Id.withoutId()).
            vendor(Vendor.CISCO).
            model(Model.XYZ0002).
            ip(IP.fromAddress("20.0.0.1")).
            location(locationA).
            routerType(RouterType.EDGE).
            switches(switchesOfEdgeRouter).
            build();
        this.routersOfCoreRouter.put(edgeRouter.getId(), edgeRouter);
        this.coreRouter = CoreRouter.builder().
            id(Id.withoutId()).
            vendor(Vendor.HP).
            model(Model.XYZ0001).
            ip(IP.fromAddress("10.0.0.1")).
            location(locationA).
            routerType(RouterType.CORE).
            routers(routersOfCoreRouter).
            build();
        this.newCoreRouter = CoreRouter.builder().
            id(Id.withId("81579b05-4b4e-4b9b-91f4-75a5a8561296")).
            vendor(Vendor.HP).
            model(Model.XYZ0001).
            ip(IP.fromAddress("10.1.0.1")).
            location(locationA).
            routerType(RouterType.CORE).
            build();
        this.newEdgeRouter = EdgeRouter.builder().
            id(Id.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")).
            vendor(Vendor.CISCO).
            model(Model.XYZ0002).
            ip(IP.fromAddress("20.1.0.1")).
            location(locationA).
            routerType(RouterType.EDGE).
            build();
    }

}