package org.cocktail.db.cocktail.enums;

public enum Method {
    BUILD("직접넣기"),
    STIR("휘젓기"),
    SHAKE("흔들기"),
    FLOAT("띄우기"),
    BLEND("블렌드"),
    MUDDLE("머들링"),
    ;

    private final String name;

    Method(String name) {
        this.name = name;
    }


}
