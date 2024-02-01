package com.github.otr.slug.framework.adapter.in;

import com.github.otr.slug.application.use_case.GenerateSlugUseCase;
import com.github.otr.slug.domain.entity.Slug;

import java.util.Scanner;

/**
 *
 */
public class GenerateSlugCliInputAdapter extends BaseInputAdapter {

    private GenerateSlugUseCase useCase;

    public GenerateSlugCliInputAdapter(GenerateSlugUseCase useCase) {
        this.useCase = useCase;
    }


    @Override
    public void invoke(String commandLineArgs) {
        Slug resultSlug = useCase.invoke(commandLineArgs);
        System.out.println(resultSlug.getValue());
    }

}
