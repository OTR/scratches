package otr.slug.framework.adapter.in.rest;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.in.BaseSlugInputAdapter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

public class SlugManagementRestInputAdapter extends BaseSlugInputAdapter {

    private static final String DEFAULT_USER_INPUT = "";
    private static final String CREATE_ENDPOINT = "/create";
    private static final Executor DEFAULT_EXECUTOR = null;
    public static final String URL_QUERY_SEPARATOR = "&";

    private HttpHandler createHandler;
    Slug slug;

    public SlugManagementRestInputAdapter(SlugManagementUseCase useCase) {
        this.useCase = useCase;
        this.createHandler = new CreateSlugHandler(this);
    }

    @Override
    public Slug invoke(Object requestParams) {
        HttpServer httpServer = parseParams(requestParams);
        httpServer.createContext(CREATE_ENDPOINT, createHandler);
        httpServer.setExecutor(DEFAULT_EXECUTOR);
        httpServer.start();
        Utils.printGreetingsLine();

        return this.slug;
    }

    void createSlug(RawInput rawInput) {
        this.slug = useCase.createSlug(rawInput);
    }

    Map<String, String> extractUserInputFromQuery(String query) {
        Map<String, List<String>> requestParams = extractParamsFrom(query);
        String userInput = getUserInputOrDefault(requestParams);
        return Map.of(USER_INPUT_PARAMS_KEY, userInput);
    }

    private static String getUserInputOrDefault(
        Map<String, List<String>> requestParams
    ) {
        String userInput = requestParams.getOrDefault(
                USER_INPUT_PARAMS_KEY,
                List.of(DEFAULT_USER_INPUT)
            )
            .stream()
            .findFirst()
            .orElse(DEFAULT_USER_INPUT);

        return userInput;
    }

    private static Map<String, List<String>> extractParamsFrom(String query) {
        Map<String, List<String>> requestParams = Pattern
            .compile(URL_QUERY_SEPARATOR)
            .splitAsStream(query)
            .map(FuncUtils.getFirstTwoElements())
            .collect(MapStringCollector.collect());

        return requestParams;
    }

    private static HttpServer parseParams(Object requestParams) {
        if (requestParams instanceof HttpServer) {
            return  (HttpServer) requestParams;
        } else {
            throw new IllegalArgumentException(
                "An instance of HttpServer should be provided"
            );
        }
    }

    RawInput wrapUserInputAccess(Map<String, String> params) {
        return super.wrapUserInput(params);
    }

}
