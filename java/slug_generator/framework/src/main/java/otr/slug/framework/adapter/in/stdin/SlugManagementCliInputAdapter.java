package otr.slug.framework.adapter.in.stdin;

import otr.slug.application.port.in.SlugManagementInputPort;
import otr.slug.application.usecase.SlugManagementUseCase;

import otr.slug.framework.adapter.in.BaseInputAdapter;

public class SlugManagementCliInputAdapter extends BaseInputAdapter {

    private SlugManagementUseCase useCase;

    public SlugManagementCliInputAdapter() {
        setPorts();
    }

    @Override
    public void invoke(String arguments) {
        // Treat arguments as a big whole input string
        useCase.createSlug();
    }

    private void setPorts() {
        this.useCase = new SlugManagementInputPort();
    }

}
