package otr.slug.framework.adapter.in.sun_http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.in.BaseSlugInputAdapter;
import otr.slug.framework.adapter.out.file.mapper.SlugJsonFileMapper;

import java.util.concurrent.Executor;

public class SlugManagementRestInputAdapter extends BaseSlugInputAdapter {

    private static final String CREATE_ENDPOINT = "/create";
    private static final Executor DEFAULT_EXECUTOR = null;

    private final HttpHandler createHandler;

    public SlugManagementRestInputAdapter(SlugManagementUseCase useCase) {
        this.useCase = useCase;
        this.createHandler = new CreateSlugHandler(this::callbackHell);
    }

    @Override
    public Slug invoke(Object requestParams) {
        HttpServer httpServer = parseParams(requestParams);
        httpServer.createContext(CREATE_ENDPOINT, createHandler);
        httpServer.setExecutor(DEFAULT_EXECUTOR);
        httpServer.start();
        Utils.printGreetingsLine();

        return null;
    }

    private String callbackHell(String userInput)  {
        RawInput rawInput = new RawInput(userInput);
        Slug slug = useCase.createSlug(rawInput);

        String slugJson = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            slugJson = mapper.writeValueAsString(
                SlugJsonFileMapper.toJson(slug)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return slugJson;
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

}
