package design.hexagonal.architecture.application.use_case;

import design.hexagonal.architecture.domain.enitity.Router;

public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);

}
