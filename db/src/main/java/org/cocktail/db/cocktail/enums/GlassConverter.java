package org.cocktail.db.cocktail.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

//@Converter(autoApply = true)
public class GlassConverter implements AttributeConverter<Glass,String> {


    @Override
    public String convertToDatabaseColumn(Glass glass) {
        return glass.getCode();
    }

    @Override
    public Glass convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }

        return Arrays.stream(Glass.values())
                .filter(e -> e.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown code: " + dbData));
    }
}
