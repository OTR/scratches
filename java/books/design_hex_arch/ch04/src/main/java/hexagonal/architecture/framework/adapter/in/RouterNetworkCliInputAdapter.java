package hexagonal.architecture.framework.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hexagonal.architecture.application.port.in.RouterNetworkInputPort;
import hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.Network;
import hexagonal.architecture.domain.vo.RouterId;
import hexagonal.architecture.framework.adapter.out.RouterNetworkFileOutputAdapter;

public class RouterNetworkCliInputAdapter extends RouterNetworkInputAdapter {

    public RouterNetworkCliInputAdapter(RouterNetworkUseCase useCase) {
        this.routerNetworkUseCase = useCase;
    }

    public Router addNetwork(RouterId routerId, Network network) {
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    private void setAdapters() {
        this.routerNetworkUseCase = new RouterNetworkInputPort(
                RouterNetworkFileOutputAdapter.getInstance()
        );
    }

    @Override
    public Router processRequest(Object requestParams) {
        var params = stdinParams(requestParams);
        this.router = this.addNetworkToRouter(params);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String routerJson = mapper.writeValueAsString(
                RouterJsonFileMapper.toJson(this.router)
            );
            System.out.println(routerJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return this.router;
    }

}
