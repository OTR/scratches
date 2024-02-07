package otr.slug.framework.adapter.in.stdin;

import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.in.BaseSlugInputAdapter;

import java.util.List;
import java.util.Map;

public class SlugManagementCliSlugInputAdapter extends BaseSlugInputAdapter {

    public SlugManagementCliSlugInputAdapter(SlugManagementUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Slug createSlug(Object requestParams) {
        Map<String, String> params = parseParams(requestParams);
        RawInput rawInput = wrapUserInput(params);
        return useCase.createSlug(rawInput);
    }

    private Map<String, String> parseParams(Object requestParams) {
        if (requestParams instanceof String userInput) {
            return Map.of(USER_INPUT_PARAMS_KEY, userInput);
        } else {
            throw new IllegalArgumentException(
                "Arguments of type: `" +
                    requestParams.getClass().getSimpleName() +
                    "` are not supported"
            );
        }
    }

}
