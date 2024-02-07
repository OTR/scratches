package otr.slug.application.port.out;

import otr.slug.domain.vo.Slug;

import java.util.List;
import java.util.UUID;

public interface SlugManagementOutputPort {

    Slug fetchSlugById(UUID slugId);

    Slug persistSlug(Slug slug);

    List<Slug> retrieveSlugs();

}
