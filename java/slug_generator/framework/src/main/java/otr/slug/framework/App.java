package otr.slug.framework;

import com.sun.net.httpserver.HttpServer;

import otr.slug.application.port.in.SlugManagementInputPort;
import otr.slug.application.port.out.SlugManagementOutputPort;
import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.in.BaseSlugInputAdapter;
import otr.slug.framework.adapter.in.sun_http.SlugManagementRestInputAdapter;
import otr.slug.framework.adapter.in.stdin.SlugManagementCliSlugInputAdapter;
import otr.slug.framework.adapter.out.file.SlugManagementFileOutputAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    private SlugManagementUseCase useCase;
    private BaseSlugInputAdapter inputAdapter;

    private App() {}

    static App getCliApp() {
        App app = new App();
        app.useCase = new SlugManagementInputPort();
        app.inputAdapter = new SlugManagementCliSlugInputAdapter(app.useCase);

        return app;
    }

    static App getCliAppWithFilePersistence() {
        App app = new App();
        SlugManagementOutputPort outputPort = SlugManagementFileOutputAdapter
            .getInstance();
        app.useCase = new SlugManagementInputPort(outputPort);
        app.inputAdapter = new SlugManagementCliSlugInputAdapter(app.useCase);

        return app;
    }

    static App getRestApp() {
        App app = new App();
        app.useCase = new SlugManagementInputPort();
        app.inputAdapter = new SlugManagementRestInputAdapter(app.useCase);
        return app;
    }

    public void runCliWithArgs(String userInput) {
        Slug slug = this.inputAdapter.createSlug(userInput);
        System.out.println(slug.value());
    }

    public void runCliFilePersistenceNoArgs(String userInput) {
        System.out.println("The user asked to display existing Slugs.");
        Slug slug = this.inputAdapter.createSlug(userInput);
        System.out.println("Output: ");
        System.out.println(slug.value());
    }

    public void runCliFilePersistenceOneArg(String userInput) {
        System.out.println("The user asked to convert raw input to Slug.");
        System.out.println("Input: ");
        System.out.println(userInput);
        Slug slug = this.inputAdapter.createSlug(userInput);
        System.out.println("Output: ");
        System.out.println(slug.value());
    }

    public void runRestWithNoArgs() {
        try {
            HttpServer httpServer = HttpServer.create(
                new InetSocketAddress(8080), 0
            );
            this.inputAdapter.createSlug(httpServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
