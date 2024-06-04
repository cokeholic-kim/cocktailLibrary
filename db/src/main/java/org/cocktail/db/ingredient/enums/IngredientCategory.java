package org.cocktail.db.ingredient.enums;

import lombok.Data;
import lombok.Getter;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.converter.AbstractCodedEnumConverter;
import org.cocktail.db.converter.CodedEnum;
@Getter
public enum IngredientCategory implements CodedEnum<String> {
    AlchoholStrong("술(고도수)"),
    AlchoholWeak("술(저도수)"),
    NonAlchohol("주스"),
    Fruit("과일"),
    Etc("기타"),
    ;

    private final String name;

    IngredientCategory(String name) {
        this.name = name;
    }

    public static IngredientCategory fromCategoryName(String name) {
        for (IngredientCategory category : values()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No matching Category enum for display name: " + name);
    }

    @Override
    public String getCode() {
        return name;
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractCodedEnumConverter<IngredientCategory, String> {
        public Converter() {
            super(IngredientCategory.class);
        }
    }
}
