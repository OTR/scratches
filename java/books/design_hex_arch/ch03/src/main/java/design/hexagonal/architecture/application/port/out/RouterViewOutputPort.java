package design.hexagonal.architecture.application.port.out;

import design.hexagonal.architecture.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();

}
