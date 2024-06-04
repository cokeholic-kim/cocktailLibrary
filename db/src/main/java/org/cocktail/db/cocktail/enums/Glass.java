package org.cocktail.db.cocktail.enums;

import lombok.Getter;
import org.cocktail.db.converter.AbstractCodedEnumConverter;
import org.cocktail.db.converter.CodedEnum;
@Getter
public enum Glass implements CodedEnum<String> {
    SHOT("샷 글라스"),
    HIGHBALL("하이볼 글라스"),
    OLDFASHIONED("올드패션 글라스"),
    COLLINS("콜린스 글라스"),
    MARTINI("마티니 글라스"),
    MAGARITA("마가리타 글라스"),
    PILSNER("필스터 글라스"),
    IRISHCOFFEE("아이리쉬 카페 글라스"),
    POUSSE("리큐르 글래스"),
    BRANDYSNIFTER("브랜디잔"),
    CORDIAL("코디얼 글래스"),
    WHITEWINE("화이트와인 글래스"),
    REDWINE("레드와인 글래스"),
    SHERRY("쉐리 글래스"),
    CHAMPAGNEFLUTE("플루트 글라스"),
    PARFAIT("파르페 글라스"),
    SOUR("사워 글라스"),
    COUPE("쿠페 글라스"),
    MARTINIGLASS("마티니 글라스");

    private final String name;

    Glass(String name) {
        this.name = name;
    }

    public static Glass fromGlassName(String glassName) {
        for (Glass glass : values()) {
            if (glass.getName().equals(glassName)) {
                return glass;
            }
        }
        throw new IllegalArgumentException("No matching Glass enum for display name: " + glassName);
    }


    @Override
    public String getCode() {
        return name;
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractCodedEnumConverter<Glass, String> {
        public Converter() {
            super(Glass.class);
        }
    }
}
