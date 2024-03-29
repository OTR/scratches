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
import otr.slug.framework.adapter.out.h2.SlugManagementH2OutputAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

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
//        SlugManagementOutputPort outputPort = SlugManagementFileOutputAdapter
//            .getInstance();
        SlugManagementOutputPort outputPort = SlugManagementH2OutputAdapter
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

    public void runCliFilePersistenceNoArgs() {
        System.out.println("The user asked to display existing Slugs.");
        List<Slug> slugs = this.inputAdapter.retrieveSlugs();
        System.out.printf("Found %d entities:%n", slugs.size());

        System.out.println("Output:");
        for (int i = 0; i < slugs.size(); i++) {
            Slug slug = slugs.get(i);
            System.out.printf("%d. %s%n", i + 1, slug.value());
        }
    }

    public void runCliFilePersistenceOneArg(String userInput) {
        System.out.println(
            "The user asked to convert raw input to Slug and persist the result"
        );
        System.out.println("Input:");
        System.out.println(userInput);
        Slug slug = this.inputAdapter.createAndPersist(userInput);
        System.out.println("Output:");
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
