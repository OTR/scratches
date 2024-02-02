package design.hexagonal.architecture;

import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.framework.adapter.in.RouterViewCliInputAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        var cli = new RouterViewCliInputAdapter();
        String type = "CORE";
        List<Router> routers = cli.obtainRelatedRouters(type);
        System.out.println(
                routers.stream()
                    .map(Router::toString)
                    .collect(Collectors.joining("\n"))
        );
    }

}
