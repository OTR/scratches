package hexagonal.architecture.framework.adapter.in;

import hexagonal.architecture.application.use_case.RouterViewUseCase;
import hexagonal.architecture.application.port.in.RouterViewInputPort;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.RouterType;
import hexagonal.architecture.framework.adapter.out.file.RouterViewFileOutputAdapter;

import java.util.List;

public class RouterViewCliInputAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCliInputAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type))
        );
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(
                RouterViewFileOutputAdapter.getInstance()
        );
    }

}
