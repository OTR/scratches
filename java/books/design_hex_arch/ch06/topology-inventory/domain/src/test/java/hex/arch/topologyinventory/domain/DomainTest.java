package hex.arch.topologyinventory.domain;

import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.exception.GenericSpecificationException;
import hex.arch.topologyinventory.domain.vo.Location;
import hex.arch.topologyinventory.domain.vo.Network;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class DomainTest {

    @Test
    public void addNetworkToSwitch() {
        Location location = createLocation("US");
        Network newNetwork = createTestNetwork("30.0.0.1", 8);
        Switch networkSwitch = createSwitch("30.0.0.0", 8, location);

        assertTrue(networkSwitch.addNetworkToSwitch(newNetwork));
    }

    @Test
    public void addNetworkToSwitch_failBecauseSameNetworkAddress() {
        Location location = createLocation("US");
        Network newNetwork = createTestNetwork("30.0.0.0", 8);
        Switch networkSwitch = createSwitch("30.0.0.0", 8, location);

        assertThrows(
            GenericSpecificationException.class,
                () -> networkSwitch.addNetworkToSwitch(newNetwork)
        );
    }

    @Test
    public void addSwitchToEdgeRouter() {
        edgeRouter.addSwitch(networkSwitch);
        assertEquals(1, edgeRouter.getSwitches().size());
    }

    @Test
    public void addSwitchToEdgeRouter_failBecauseEquipmentOfDifferentCountries() {
        Location locationUS = createLocation("US");
        Location locationJP = createLocation("JP");
        Switch networkSwitch = createSwitch("30.0.0.0", 8, locationUS);
        assertThrows(
            GenericSpecificationException.class,
            () -> edgeRouter.addSwitch(networkSwitch)
        );
    }

}
