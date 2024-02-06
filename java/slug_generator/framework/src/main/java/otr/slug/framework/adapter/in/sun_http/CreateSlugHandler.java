package otr.slug.framework.adapter.in.sun_http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.function.Function;

class CreateSlugHandler implements HttpHandler {

    private static final String GET_METHOD = "GET";
    private static final int WITH_NO_BODY = -1;
    private static final int HTTP_OK = 200;
    private static final int HTTP_BAD_METHOD = 405;
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private final Function<String, String> callbackHell;

    CreateSlugHandler(Function<String, String> callbackHell) {
        this.callbackHell = callbackHell;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals(GET_METHOD)) {
            String query = exchange.getRequestURI().getRawQuery();
            Map<String, String> params = ParamsHandler.extractUserInputFromQuery(query);

            String adapterRequest = ParamsHandler.retrieveUserInputFrom(params);
            String adapterResponse = callbackHell.apply(adapterRequest);

            exchange.getResponseHeaders()
                .set(CONTENT_TYPE, APPLICATION_JSON);
            exchange.sendResponseHeaders(
                HTTP_OK, adapterResponse.getBytes().length
            );
            OutputStream output = exchange.getResponseBody();
            output.write(adapterResponse.getBytes());
            output.flush();
        } else {
            exchange.sendResponseHeaders(HTTP_BAD_METHOD, WITH_NO_BODY);
        }

        exchange.close();
    }

}
