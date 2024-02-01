package design.hexagonal.architecture.framework.adapter.in;

import design.hexagonal.architecture.application.use_case.RouterViewUseCase;
import design.hexagonal.architecture.application.port.in.RouterViewInputPort;
import design.hexagonal.architecture.domain.entity.Router;
import design.hexagonal.architecture.framework.adapter.out.RouterViewFileOutputAdapter;

import java.util.List;

public class RouterViewCliInputAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCliInputAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        RelatedRoutersCommand relatedRoutersCommand =
            new RelatedRoutersCommand(type);

        return routerViewUseCase.getRelatedRouters(relatedRoutersCommand);
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(
            RouterViewFileOutputAdapter.getInstance()
        );
    }

}
