package hexagonal.architecture.domain.service;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.RouterType;

import java.util.List;
import java.util.stream.Collectors;

public class RouterSearchService {

    public static List<Router> getRouters(
            List<Router> routers,
            RouterType type
    ) {
        return routers.stream()
            .filter(router -> router.isType(type))
            .collect(Collectors.<Router>toList());
    }

}
