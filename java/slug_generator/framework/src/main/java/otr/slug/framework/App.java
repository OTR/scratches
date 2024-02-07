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
        Slug slug = this.inputAdapter.invoke(userInput);
        System.out.println(slug.value());
    }

    public void runCliAppWithFilePersistence(String userInput) {
        Slug slug = this.inputAdapter.invoke(userInput);
        System.out.println("Result: ");
        System.out.println(slug.value());
        System.out.println("The result bas been persisted to a file: ");
        System.out.println("$HOME/file-storage-something.json");
    }

    public void runRestWithNoArgs() {
        try {
            HttpServer httpServer = HttpServer.create(
                new InetSocketAddress(8080), 0
            );
            this.inputAdapter.invoke(httpServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
