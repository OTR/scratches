package otr.slug.application.port.out;

import otr.slug.domain.vo.Slug;

import java.util.UUID;

public interface SlugManagementOutputPort {

    Slug fetchSlugById(UUID slugId);

    boolean persistSlug(Slug slug);

}
