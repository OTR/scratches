package design.hexagonal.architecture;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.framework.adapter.in.RouterViewCliInputAdapter;

import java.util.List;
import java.util.stream.Collectors;


public class AppTest {

    public static void main(String[] args) {
        testCliInputAdapter();
    }

    /**
     * End-to-end smoke test that ensures that refactoring hasn't affected
     * general Application login (Cli Adapter)
     */
    public static void testCliInputAdapter() {
        // GIVEN
        var cli = new RouterViewCliInputAdapter();
        String type = "CORE";
        List<Router> routers = cli.obtainRelatedRouters(type);
        String expected = "Router{routerType=CORE, routerId=RouterId{id=d3adb33f-4b4e-4b9b-91f4-75a5a8561296}}\n" +
                "Router{routerType=CORE, routerId=RouterId{id=0ctopus-4b4e-4b9b-91f4-75a5a8561296}}";

        // WHEN
        String actual = routers.stream()
                    .map(Router::toString)
                    .collect(Collectors.joining("\n"));

        // THEN
        if (!actual.equals(expected)) {
            throw new AssertionError();
        };
    }

}
