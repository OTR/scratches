package otr.slug.framework.adapter.out.h2.data;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import otr.slug.domain.spec.AlphaDecimalSpecification;
import otr.slug.domain.spec.ValidYearSpecification;
import otr.slug.domain.spec.shared.Specification;
import otr.slug.framework.adapter.out.file.mapper.UuidProvider;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "slugs")
public class SlugData implements Serializable {

    @Id
    @Column(name = "slug_id", updatable = false)
    @Convert(converter = UUIDTypeConverter.class)
    private UUID slugId;

    @Column(name = "slug_value")
    private String slugValue;

    public SlugData() {}

    public SlugData(UUID slugId, String slugValue) {
        this.slugId = slugId;
        this.slugValue = slugValue;
    }

    public static SlugData valueOf(String slugValue) {
        Specification<String> alphaDecimal = new AlphaDecimalSpecification();
        Specification<String> validYear = new ValidYearSpecification();

        alphaDecimal.and(validYear).check(slugValue);

        UUID slugId = UuidProvider.fromString(slugValue);

        return new SlugData(slugId, slugValue);
    }

    public UUID getSlugId() {
        return slugId;
    }

    public String getSlugValue() {
        return slugValue;
    }

}
