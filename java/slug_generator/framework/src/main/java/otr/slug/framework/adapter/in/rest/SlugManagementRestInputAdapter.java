package otr.slug.framework.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.in.BaseSlugInputAdapter;
import otr.slug.framework.adapter.out.file.mapper.SlugJsonFileMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

public class SlugManagementRestInputAdapter extends BaseSlugInputAdapter {

    public SlugManagementRestInputAdapter(SlugManagementUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Slug invoke(Object requestParams) {
        Map<String, String> params = parseUserInput(requestParams);

        HttpServer httpServer = null;
        httpServer.createContext("/create", CREATE_HANDLER);


        return new Slug("");
    }

    private Map<String, String> parseUserInput(Object requestParams) {
        return Collections.emptyMap();
    }

    private final HttpHandler CREATE_HANDLER = new HttpHandler() {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (exchange.getRequestMethod().equals("GET")) {
                String query = exchange.getRequestURI().getRawQuery();
                var params = Collections.<String, String>emptyMap();
                httpParams(query, params);
                RawInput rawInput = wrapUserInput(params);
                Slug slug = useCase.createSlug(rawInput);

                ObjectMapper mapper = new ObjectMapper();
                String slugJson = mapper.writeValueAsString(
                    SlugJsonFileMapper.toJson(slug)
                );

                exchange.getResponseHeaders()
                    .set("Content-Type", "application/json");
                exchange.sendResponseHeaders(
                    200, slugJson.getBytes().length
                );
                OutputStream output = exchange.getResponseBody();
                output.write(slugJson.getBytes());
                output.flush();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }

    };


}
