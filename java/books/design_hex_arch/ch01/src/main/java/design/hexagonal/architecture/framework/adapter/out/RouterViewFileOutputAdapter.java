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

    public static RouterViewOutputPort getInstance() {
        throw new NotImplementedYet();
    }

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
                                .getResourceAsStream("routers")
                    )
                )
            ).lines()
        ) {
            stream.forEach(line -> {
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Router router = new Router(
                    RouterType.valueOf(type), RouterId.of(id)
                );
                routers.add(router);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return routers;
    }

}
