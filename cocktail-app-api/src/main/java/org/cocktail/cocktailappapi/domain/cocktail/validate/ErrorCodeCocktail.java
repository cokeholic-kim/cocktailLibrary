package org.cocktail.cocktailappapi.domain.cocktail.validate;

import org.cocktail.common.error.ErrorCodeIfs;

public enum ErrorCodeCocktail implements ErrorCodeIfs {
    NULL_COCKTAIL(400,201,"요청한 칵테일의 정보가 없습니다."),
    EMPTY_COCKTAIL(500,202,"칵테일 리스트가 조회되지않았습니다.")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCodeCocktail(Integer httpStatusCode, Integer errorCode, String description) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.description = description;
    }
    ;

    @Override
    public Integer getHttpStatusCode() {
        return null;
    }

    @Override
    public Integer getErrorCode() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
