package hex.arch.topologyinventory.framework;

import hex.arch.topologyinventory.domain.entity.EdgeRouter;
import hex.arch.topologyinventory.domain.entity.Switch;
import hex.arch.topologyinventory.domain.vo.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwitchTest extends FrameworkTestData {

    public SwitchTest() {
        loadPortsAndUseCases();
        loadData();
    }

    @Test
    @Order(1)
    public void retrieveSwitch(){
        Id switchId = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb47");
        Switch networkSwitch = this.switchIA
            .retrieveSwitch(switchId);
        assertNotNull(networkSwitch);
    }

    @Test
    @Order(2)
    public void createAndAddSwitchToEdgeRouter(){
        String expectedSwitchIP = "15.0.0.1";
        Id id = Id.withId("b07f5187-2d82-4975-a14b-bdbad9a8ad46");

        EdgeRouter edgeRouter = this.switchIA
            .createAndAddSwitchToEdgeRouter(
            Vendor.HP,
            Model.XYZ0004,
            IP.fromAddress(expectedSwitchIP),
            this.locationA,
            SwitchType.LAYER3,
            id);

        String actualSwitchIP = edgeRouter.getSwitches()
            .entrySet()
            .stream()
            .map(entry -> entry.getValue())
            .map(aSwitch -> aSwitch.getIp().getIpAddress())
            .filter(ipAddress -> ipAddress.equals(expectedSwitchIP))
            .findFirst()
            .get();

        assertEquals(expectedSwitchIP,actualSwitchIP);
    }

    @Test
    @Order(3)
    public void removeSwitchFromEdgeRouter(){
        Id switchId = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb47");
        Id edgerRouterId = Id.withId("b07f5187-2d82-4975-a14b-bdbad9a8ad46");
        EdgeRouter edgeRouter = this.switchIA
            .removeSwitchFromEdgeRouter(switchId, edgerRouterId);
        assertNull(edgeRouter.getSwitches().get(switchId));
    }

}