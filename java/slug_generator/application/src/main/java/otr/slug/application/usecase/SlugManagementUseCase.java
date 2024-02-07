package otr.slug.application.usecase;


import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

import java.util.List;

public interface SlugManagementUseCase {

    Slug createSlug(RawInput rawInput);

    List<Slug> retrieveSlugs();

    Slug persistSlug(Slug slug);

}
