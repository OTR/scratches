package otr.slug.framework.adapter.out.h2.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "slugs")
@Converter(name = "uuidConverter", converterClass = UUIDTypeConverter.class)
public class SlugData implements Serializable {

    @Id
    @Column(name = "slug_id", columnDefinition = "uuid", updatable = false)
    @Convert(value = "uuidConverter")
    private UUID slugId;

    @Column(name = "slug_value")
    private String slugValue;

    public SlugData() {}

    public SlugData(UUID slugId, String slugValue) {
        this.slugId = slugId;
        this.slugValue = slugValue;
    }

    public UUID getSlugId() {
        return slugId;
    }

    public String getSlugValue() {
        return slugValue;
    }

}
