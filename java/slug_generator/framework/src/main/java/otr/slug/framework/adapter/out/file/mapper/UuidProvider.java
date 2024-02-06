package otr.slug.framework.adapter.out.file.mapper;

import java.util.UUID;

public class UuidProvider {

    public static UUID fromString(String value) {
        return UUID.nameUUIDFromBytes(value.getBytes());
    }

}
