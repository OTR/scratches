package otr.slug.framework.adapter.out.file.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import otr.slug.domain.spec.AlphaDecimalSpecification;
import otr.slug.domain.spec.ValidYearSpecification;
import otr.slug.domain.spec.shared.Specification;

import otr.slug.framework.adapter.out.file.mapper.UuidProvider;

import java.util.UUID;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SlugJson {

    @JsonProperty(value = "slug_id")
    private UUID slugId;

    @JsonProperty(value = "slug_value")
    private String slugValue;

    public SlugJson() {}

    private SlugJson(UUID slugId, String slugValue) {
        this.slugId = slugId;
        this.slugValue = slugValue;
    }

    public UUID getSlugId() {
        return slugId;
    }

    public String getSlugValue() {
        return slugValue;
    }

    public static SlugJson valueOf(String value) {
        Specification<String> alphaDecimal = new AlphaDecimalSpecification();
        Specification<String> validYear = new ValidYearSpecification();

        alphaDecimal.and(validYear).check(value);

        UUID uuid = UuidProvider.fromString(value);

        return new SlugJson(uuid, value);
    }

}
