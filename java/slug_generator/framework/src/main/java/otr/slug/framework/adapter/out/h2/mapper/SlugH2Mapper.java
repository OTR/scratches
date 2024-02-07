package otr.slug.framework.adapter.out.h2.mapper;

import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.out.h2.data.SlugData;

import java.util.List;

public class SlugH2Mapper {

    public static Slug toDomain(SlugData slugData) {
        return new Slug(slugData.getSlugValue());
    }

    public static SlugData toH2(Slug slug) {
        return SlugData.valueOf(slug.value());
    }

    public static List<Slug> getSlugsFromData(List<SlugData> slugDatas) {
        return slugDatas.stream()
            .map(SlugData::getSlugValue)
            .map(Slug::new)
            .toList();
    }

    public static List<SlugData> getSlugsFromDomain(List<Slug> slugs) {
        return slugs.stream()
            .map(Slug::value)
            .map(SlugData::valueOf)
            .toList();
    }

}
