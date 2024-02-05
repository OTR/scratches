package otr.slug.framework.adapter.in.stdin;

import otr.slug.application.port.in.SlugManagementInputPort;
import otr.slug.application.usecase.SlugManagementUseCase;

import otr.slug.domain.vo.RawInput;
import otr.slug.framework.adapter.in.BaseInputAdapter;

public class SlugManagementCliInputAdapter extends BaseInputAdapter {

    private SlugManagementUseCase useCase;

    public SlugManagementCliInputAdapter() {
        setPorts();
    }

    @Override
    public void invoke(Object arguments) {
        // Treat arguments as a big whole input string
        if (arguments instanceof String userInput) {
            RawInput rawInput = new RawInput(userInput);
            useCase.createSlug(rawInput);
        } else {
            throw new IllegalArgumentException(
                "Arguments of type: `" +
                arguments.getClass().getSimpleName() +
                "` are not supported"
            );
        }
    }

    private void setPorts() {
        this.useCase = new SlugManagementInputPort();
    }

}
