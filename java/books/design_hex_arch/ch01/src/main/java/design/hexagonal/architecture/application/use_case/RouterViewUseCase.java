package design.hexagonal.architecture.application.use_case;

import design.hexagonal.architecture.domain.entity.Router;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);

}
