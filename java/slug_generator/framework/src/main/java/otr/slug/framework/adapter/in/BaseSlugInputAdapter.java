package otr.slug.framework.adapter.in;

import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

import java.util.List;
import java.util.Map;

public abstract class BaseSlugInputAdapter {

    protected static final String USER_INPUT_PARAMS_KEY = "user_input";

    protected SlugManagementUseCase useCase;

    public abstract Slug createSlug(Object requestParams);

    // TODO: Launch Input Adapter in infinity event loop mode
    //public abstract void invoke(Object requestParams);

    protected RawInput wrapUserInput(Map<String, String> params) {
        String userInput = params.get(USER_INPUT_PARAMS_KEY);
        return new RawInput(userInput);
    }

    public List<Slug> retrieveSlugs() {
        return this.useCase.retrieveSlugs();
    };

}
