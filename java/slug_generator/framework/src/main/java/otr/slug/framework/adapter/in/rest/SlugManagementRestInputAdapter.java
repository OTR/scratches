package otr.slug.framework.adapter.in.rest;

import otr.slug.application.usecase.SlugManagementUseCase;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.in.BaseSlugInputAdapter;

public class SlugManagementRestInputAdapter extends BaseSlugInputAdapter {

    public SlugManagementRestInputAdapter(SlugManagementUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Slug invoke(Object requestParams) {
        System.out.println("I AM CHAMPION!");
        return new Slug("");
    }

}
