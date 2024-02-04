package otr.slug.domain.service;

import otr.slug.domain.policy.FilterStringPolicy;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

import java.util.List;

public class SlugService {

    public static Slug createSlug(
        RawInput rawInput,
        List<FilterStringPolicy> policies
    ) {
        String output = rawInput.value();
        for (FilterStringPolicy policy : policies) {
            output = policy.filter(output);
        }
        return new Slug(output);
    }

}
