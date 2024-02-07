package otr.slug.framework.adapter.out.file.mapper;

import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.out.file.json.SlugJson;

import java.util.List;

public class SlugJsonFileMapper {

    public static SlugJson toJson(Slug slug) {
        return SlugJson.valueOf(slug.value());
    }

    public static Slug toDomain(SlugJson slugJson) {
        return new Slug(slugJson.getSlugValue());
    }

    public static List<Slug> getSlugsFromJson(List<SlugJson> slugJsons) {
        return slugJsons.stream()
            .map(SlugJson::getSlugValue)
            .map(Slug::new)
            .toList();
    }

    public static List<SlugJson> getSlugsFromDomain(List<Slug> slugs) {
        return slugs.stream()
            .map(Slug::value)
            .map(SlugJson::valueOf)
            .toList();
    }

}
