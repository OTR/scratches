package com.github.otr.slug.application.use_case;

import com.github.otr.slug.domain.entity.Slug;

/**
 *
 */
public interface GenerateSlugUseCase {

    public Slug invoke(String inputString);

}
