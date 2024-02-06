package otr.slug.framework;

import com.sun.net.httpserver.HttpServer;

import otr.slug.application.port.in.SlugManagementInputPort;
import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.in.BaseSlugInputAdapter;
import otr.slug.framework.adapter.in.sun_http.SlugManagementRestInputAdapter;
import otr.slug.framework.adapter.in.stdin.SlugManagementCliSlugInputAdapter;

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

    static App getRestApp() {
        App app = new App();
        app.useCase = new SlugManagementInputPort();
        app.inputAdapter = new SlugManagementRestInputAdapter(app.useCase);
        return app;
    }

    public void runCliWithArgs(String commandLineArgs) {
        Slug slug = this.inputAdapter.invoke(commandLineArgs);
        System.out.println(slug.value());
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
