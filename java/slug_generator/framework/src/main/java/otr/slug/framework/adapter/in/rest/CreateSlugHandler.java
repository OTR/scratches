package otr.slug.framework.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import otr.slug.domain.vo.RawInput;
import otr.slug.framework.adapter.out.file.mapper.SlugJsonFileMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class CreateSlugHandler implements HttpHandler {

        private final SlugManagementRestInputAdapter caller;

    CreateSlugHandler(SlugManagementRestInputAdapter caller) {
        this.caller = caller;
    }

    @Override
        public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            String query = exchange.getRequestURI().getRawQuery();
            Map<String, String> params = caller.extractUserInputFromQuery(query);
            RawInput rawInput = caller.wrapUserInputAccess(params);
            caller.createSlug(rawInput);

            ObjectMapper mapper = new ObjectMapper();
            String slugJson = mapper.writeValueAsString(
                SlugJsonFileMapper.toJson(caller.slug)
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

        exchange.close();
    }

}
