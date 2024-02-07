package otr.slug.framework.adapter.out.h2.data;

import java.util.UUID;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UUIDTypeConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String string) {
        return string == null ? null : UUID.fromString(string);
    }

}
