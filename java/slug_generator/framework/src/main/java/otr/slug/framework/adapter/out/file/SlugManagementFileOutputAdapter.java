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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SlugManagementFileOutputAdapter
    implements SlugManagementOutputPort {

    private static SlugManagementFileOutputAdapter instance;

    private final ObjectMapper objectMapper;
    private final InputStream resource;
    private List<SlugJson> slugJsons;

    @Override
    public Slug fetchSlugById(UUID slugId) {

        for (SlugJson slugJson : slugJsons) {
            if (slugJson.getSlugId().equals(slugId)) {
                return SlugJsonFileMapper.toDomain(slugJson);
            }
        }

        throw new IllegalArgumentException("There is no Slug with such Id");
    }

    @Override
    public Slug persistSlug(Slug slug) {
        SlugJson slugJson = SlugJsonFileMapper.toJson(slug);

        this.updateSlugJsonsWith(slugJson);

        try {
            File file = getTmpJsonFile();
            boolean isDeleted = file.delete();
            this.objectMapper.writeValue(file, slugJsons);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return slug;
    }

    @Override
    public List<Slug> retrieveSlugs() {
        return SlugJsonFileMapper.getSlugsFromJson(slugJsons);
    }

    private void readJsonFile() {
        var typeReference = new TypeReference<List<SlugJson>>() {};
        try {
            File tmpJsonFile = getTmpJsonFile();
            if (tmpJsonFile.exists()) {
                this.slugJsons = objectMapper.readValue(
                    tmpJsonFile, typeReference
                );
            } else {
                this.slugJsons = objectMapper.readValue(
                    this.resource, typeReference
                );
            }
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

    private File getTmpJsonFile() {
        Path tmpJsonPath = Paths
            .get(System.getProperty("java.io.tmpdir"))
            .resolve(".app_data")
            .resolve("slugs.json");
        File file = tmpJsonPath.toFile();

        return file;
    }

    private void updateSlugJsonsWith(SlugJson slugJson) {
        this.slugJsons = Stream.concat(
                this.slugJsons.stream(), Stream.of(slugJson)
            )
            .distinct()
            .collect(Collectors.toList());
    }

}
