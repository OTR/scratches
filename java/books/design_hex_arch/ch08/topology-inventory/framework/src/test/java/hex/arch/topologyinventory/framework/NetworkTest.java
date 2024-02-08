package hex.arch.topologyinventory.framework;

import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.service.NetworkService;
import hex.arch.topologyinventory.domain.vo.Id;
import hex.arch.topologyinventory.domain.vo.Network;
import hex.arch.topologyinventory.framework.adapter.in
    .NetworkManagementGenericInputAdapter;
import hex.arch.topologyinventory.framework.adapter.in
    .SwitchManagementGenericInputAdapter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
    import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NetworkTest extends FrameworkTestData {

    NetworkManagementGenericInputAdapter networkManagementGenericAdapter;
    SwitchManagementGenericInputAdapter switchManagementGenericAdapter;

    public NetworkTest(){
        this.networkManagementGenericAdapter
            = new NetworkManagementGenericInputAdapter();
        this.switchManagementGenericAdapter
            = new SwitchManagementGenericInputAdapter();
        loadData();
    }
    @Test
    @Order(1)
    public void addNetworkToSwitch(){
        Id switchId = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb46");
        Switch networkSwitch = networkManagementGenericAdapter
            .addNetworkToSwitch(network, switchId);
        Predicate<Network> predicate = Network
            .getNetworkNamePredicate("TestNetwork");
        Network actualNetwork = NetworkService
            .findNetwork(networkSwitch.getSwitchNetworks(), predicate);
        assertEquals(network, actualNetwork);
    }

    @Disabled // FIXME:
    @Test
    @Order(2)
    public void removeNetworkFromSwitch(){
        Id switchId = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb46");
        var networkName = "HR";
        Predicate<Network> predicate = Network
            .getNetworkNamePredicate(networkName);
        Switch networkSwitch = switchManagementGenericAdapter
            .retrieveSwitch(switchId);
        Network existentNetwork = NetworkService
            .findNetwork(networkSwitch.getSwitchNetworks(), predicate);
        assertNotNull(existentNetwork);
        networkSwitch = networkManagementGenericAdapter
            .removeNetworkFromSwitch(networkName, switchId);
        assertNull(networkSwitch);
    }
}
