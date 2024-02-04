package otr.slug.application.port.in;

import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

public class SlugManagementInputPort implements SlugManagementUseCase {

    private Object outputPort; // Placeholder

    public SlugManagementInputPort(Object outputPort) {
        this.outputPort = outputPort;
    }

    public SlugManagementInputPort() {}

    @Override
    public Slug createSlug(RawInput rawInput) {
        return null;
    }

}
