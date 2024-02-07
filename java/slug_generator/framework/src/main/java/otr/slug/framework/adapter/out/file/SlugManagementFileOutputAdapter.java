package otr.slug.framework.adapter.out.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import otr.slug.application.port.out.SlugManagementOutputPort;
import otr.slug.domain.exception.BaseCustomException;
import otr.slug.domain.vo.Slug;
import otr.slug.framework.adapter.out.file.json.SlugJson;
import otr.slug.framework.adapter.out.file.mapper.SlugJsonFileMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class SlugManagementFileOutputAdapter
    implements SlugManagementOutputPort {

    private static SlugManagementFileOutputAdapter instance;

    private final ObjectMapper objectMapper;
    private final InputStream resource;
    private List<SlugJson> slugs;

    @Override
    public Slug fetchSlugById(UUID slugId) {

        for (SlugJson slugJson : slugs) {
            if (slugJson.getSlugId().equals(slugId)) {
                return SlugJsonFileMapper.toDomain(slugJson);
            }
        }

        throw new IllegalArgumentException("There is no Slug with such Id");
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

    private void readJsonFile() {
        try {
            this.slugs = objectMapper.readValue(
                this.resource,
                new TypeReference<List<SlugJson>>() {}
            );
        } catch (Exception e) {
            throw new BaseCustomException(e);
        }
    }

    private SlugManagementFileOutputAdapter() {
        this.objectMapper = new ObjectMapper();
        this.resource = getClass()
            .getClassLoader()
            .getResourceAsStream("slugs.json");

        readJsonFile();
    }

    public static SlugManagementFileOutputAdapter getInstance() {
        if (instance == null) {
            instance = new SlugManagementFileOutputAdapter();
        }
        return instance;
    }

}
