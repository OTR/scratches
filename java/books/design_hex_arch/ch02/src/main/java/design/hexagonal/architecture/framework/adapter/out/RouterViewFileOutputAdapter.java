package design.hexagonal.architecture.framework.adapter.out;

import design.hexagonal.architecture.application.port.out.RouterViewOutputPort;
import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.domain.exception.NotImplementedYet;
import design.hexagonal.architecture.domain.vo.RouterId;
import design.hexagonal.architecture.domain.vo.RouterType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RouterViewFileOutputAdapter implements RouterViewOutputPort {

    private static RouterViewFileOutputAdapter instance;

    @Override
    public List<Router> fetchRouters() {
        return readFileAsString();
    }

    private static List<Router> readFileAsString() {
        List<Router> routers = new ArrayList<>();

        try(
            Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                    Objects.requireNonNull(
                        RouterViewFileOutputAdapter
                            .class
                            .getClassLoader()
                            .getResourceAsStream("routers.txt")
                    )
                )
            ).lines()
        ) {
            stream.forEach(line -> {
                String[] routerEntry = line.split(";");
                String id = routerEntry[0];
                String type = routerEntry[1];
                Router router = new Router(
                    RouterType.valueOf(type), RouterId.withId(id)
                );
                routers.add(router);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return routers;
    }

    private RouterViewFileOutputAdapter() {}

    public static RouterViewOutputPort getInstance() {
        if (instance == null) {
            instance = new RouterViewFileOutputAdapter();
        }
        return instance;
    }

}
