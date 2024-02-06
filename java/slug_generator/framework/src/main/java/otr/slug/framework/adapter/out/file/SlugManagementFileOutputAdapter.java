package otr.slug.framework.adapter.out.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import otr.slug.application.port.out.SlugManagementOutputPort;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.out.file.json.SlugJson;
import otr.slug.framework.adapter.out.file.mapper.SlugJsonFileMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class SlugManagementFileOutputAdapter implements SlugManagementOutputPort {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Slug fetchSlugById(UUID slugId) {
        Slug slug;

        for (SlugJson slugJson : slugs) {
            if (slugJson.getSlugId().equals(slugId)) {
                slug = SlugJsonFileMapper.toDomain(slugJson);
                break;
            }
        }

        return slug;
    }

    @Override
    public boolean persistSlug(Slug slug) {
        SlugJson slugJson = SlugJsonFileMapper.toJson(slug);

        try {
            String localDir = Paths.get("").toAbsolutePath().toString();
            File file = new File(localDir + "/slugs.json");
            file.delete();
            this.objectMapper.writeValue(file, slugJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
