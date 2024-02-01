package com.github.otr.slug;

import com.github.otr.slug.application.port.in.GenerateSlugInputPort;
import com.github.otr.slug.application.use_case.GenerateSlugUseCase;
import com.github.otr.slug.framework.adapter.in.BaseInputAdapter;
import com.github.otr.slug.framework.adapter.in.GenerateSlugCliInputAdapter;

/**
 *
 */
public class App {

    private GenerateSlugUseCase useCase;
    private BaseInputAdapter inputAdapter;

    static App getCliApp() {
        App app = new App();
        app.useCase = new GenerateSlugInputPort();
        app.inputAdapter = new GenerateSlugCliInputAdapter(app.useCase);

        return app;
    }

    public void runWithArgs(String commandLineArgs) {
        this.inputAdapter.invoke(commandLineArgs);
    }

}
