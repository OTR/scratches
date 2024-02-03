package hexagonal.architecture.framework.adapter.in.stdin;

import hexagonal.architecture.application.use_case.RouterViewUseCase;
import hexagonal.architecture.application.use_case.RouterViewUseCase.RelatedRoutersCommand;
import hexagonal.architecture.application.port.in.RouterViewInputPort;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.framework.adapter.out.file.RouterViewFileOutputAdapter;

import java.util.List;

public class RouterViewCliInputAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCliInputAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        RelatedRoutersCommand command = new RelatedRoutersCommand(type);
        return routerViewUseCase.getRelatedRouters(command);
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(
                RouterViewFileOutputAdapter.getInstance()
        );
    }

}
