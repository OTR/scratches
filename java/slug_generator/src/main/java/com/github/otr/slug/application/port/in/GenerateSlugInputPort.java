package com.github.otr.slug.application.port.in;

import com.github.otr.slug.application.use_case.GenerateSlugUseCase;
import com.github.otr.slug.domain.entity.Slug;

/**
 *
 */
public class GenerateSlugInputPort implements GenerateSlugUseCase {

    @Override
    public Slug invoke(String inputString) {
        return Slug.valueOf(inputString);
    }

}
