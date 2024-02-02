package hexagonal.architecture.framework.adapter.in;

import hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import hexagonal.architecture.domain.entity.Router;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class RouterNetworkRestInputAdapter extends RouterNetworkInputAdapter {

    public RouterNetworkRestInputAdapter(RouterNetworkUseCase useCase) {
        this.routerNetworkUseCase = useCase;
    }

    @Override
    public Router processRequest(Object requestParams) {
        Map<String, String> params = new HashMap<>();

        if (requestParams instanceof HttpServer) {
            var httpserver = (HttpServer) requestParams;

            httpserver.createContext("/network/add", (exchange -> {
                if ("GET".equals(exchange.getRequestMethod())) {
                    var query = exchange.getRequestURI().getRawQuery();
                    httpParams(query, params);
                    this.router = this.addNetworkToRouter(params);
                    ObjectMapper mapper = new ObjectMapper();
                    Json routerJson = mapper.writeValueAsString(
                        RouterJsonFileMapper.toJson(router)
                    );
                    exchange.getResponseHeaders()
                        .set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(
                        200, routerJson.getBytes().length
                    );
                    OutputStream output = exchange.getResponseBody();
                    output.write(routerJson.getBytes());
                    output.flush();
                } else {
                    exchange.sendResponseHeaders(405, -1);
                }

                exchange.close();
            }));

            httpserver.setExecutor(null);
            httpserver.start();
        }

        return this.router;
    }

}
