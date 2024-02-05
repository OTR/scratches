package otr.slug.framework;

import otr.slug.application.port.in.SlugManagementInputPort;
import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.in.BaseSlugInputAdapter;
import otr.slug.framework.adapter.in.stdin.SlugManagementCliSlugInputAdapter;

/**
 *
 */
public class App {

    private SlugManagementUseCase useCase;
    private BaseSlugInputAdapter inputAdapter;

    static App getCliApp() {
        App app = new App();
        app.useCase = new SlugManagementInputPort();
        app.inputAdapter = new SlugManagementCliSlugInputAdapter(app.useCase);

        return app;
    }

    public void runWithArgs(String commandLineArgs) {
        Slug slug = this.inputAdapter.invoke(commandLineArgs);
        System.out.println(slug.value());
    }

}
