package design.hexagonal.architecture.framework.adapter.in;

import design.hexagonal.architecture.application.use_case.RouterViewUseCase;

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
