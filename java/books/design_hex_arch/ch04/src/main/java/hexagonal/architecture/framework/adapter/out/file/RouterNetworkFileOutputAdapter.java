package hexagonal.architecture.framework.adapter.out.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import hexagonal.architecture.application.port.out.RouterNetworkOutputPort;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.RouterId;
import hexagonal.architecture.framework.adapter.out.file.data.RouterJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RouterNetworkFileOutputAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkFileOutputAdapter instance;
    private List<RouterJson> routers = new ArrayList<>();
    private ObjectMapper objectMapper;

    @Override
    public Router fetchRouterById(RouterId routerId) {
        Router router = new Router();
        for (RouterJson routerJson : routers) {
            if (routerJson.getRouterId().equals(routerId.getUUID())) {
                router = RouterJsonFileMapper.toDomain(routerJson);
                break;
            }
        }

        return router;
    }

    @Override
    public boolean persistRouter(Router router) {
        RouterJson routerJson = RouterJsonFileMapper.toJson(router);
        try {
            String localDir = Paths.get("").toAbsolutePath().toString();
            File file = new File(localDir + "/inventory.json");
            file.delete();
            objectMapper.writeValue(file, routerJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void readJsonFile() {
        try {
            this.routers = this.objectMapper.readValue(
                    this.resource,
                    new TypeReference<List<RouterJson>>() {}
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RouterNetworkFileOutputAdapter() {
        readJsonFile();
    }

    public static RouterNetworkFileOutputAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileOutputAdapter();
        }
        return instance;
    }

}
