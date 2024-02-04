package otr.slug.application.usecase;


import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

public interface SlugManagementUseCase {

    Slug createSlug(RawInput rawInput);

}
