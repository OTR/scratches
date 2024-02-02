package hexagonal.architecture.domain.service;

import hexagonal.architecture.domain.entity.Router;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RouterSearchService {

    public static List<Router> retrieveRouter(
            List<Router> routers,
            Predicate<Router> predicate
    ) {
        return routers.stream()
            .filter(predicate)
            .collect(Collectors.<Router>toList());
    }

}
