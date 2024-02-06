package otr.slug.framework.adapter.out.file.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SlugJson {

    @JsonProperty(value = "slug_id")
    private UUID slugId;

    @JsonProperty(value = "slug_value")
    private String slugValue;

    public UUID getSlugId() {
        return slugId;
    }

    public String getSlugValue() {
        return slugValue;
    }

}
