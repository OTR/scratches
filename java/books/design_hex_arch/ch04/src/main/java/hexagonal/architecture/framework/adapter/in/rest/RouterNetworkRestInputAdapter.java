package hexagonal.architecture.framework.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import hexagonal.architecture.application.use_case.RouterNetworkUseCase;
import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.RouterId;
import hexagonal.architecture.framework.adapter.in.RouterNetworkInputAdapter;
import hexagonal.architecture.framework.adapter.out.file.RouterJsonFileMapper;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

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
                    String routerJson = mapper.writeValueAsString(
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

    private void httpParams(String query, Map<String, String> params) {
        String noNameText = "Anonymous";
        Map<String, List<String>> requestParams = Pattern.compile("&")
            .splitAsStream(query)
            .map(s -> Arrays.copyOf(s.split("="), 2))
            .collect(
                groupingBy(
                    s -> decode(s[0]),
                    mapping(s -> decode(s[1]), toList())
                )
            );

        RouterId routerId = requestParams.getOrDefault(
                "routerId", List.of
        )
    }

    private static String decode(final String encoded) {
        try {
            return (encoded == null) ? null
                : URLDecoder.decode(encoded, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is a required encoding", e);
        }
    }

}
