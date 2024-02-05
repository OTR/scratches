package otr.slug.framework.adapter.in;

import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

import java.util.Map;

public abstract class BaseSlugInputAdapter {

    protected static final String USER_INPUT_PARAMS_KEY = "user_input";

    protected SlugManagementUseCase useCase;

    public abstract Slug invoke(Object requestParams);

    protected RawInput wrapUserInput(Map<String, String> params) {
        String userInput = params.get(USER_INPUT_PARAMS_KEY);
        return new RawInput(userInput);
    }

}
