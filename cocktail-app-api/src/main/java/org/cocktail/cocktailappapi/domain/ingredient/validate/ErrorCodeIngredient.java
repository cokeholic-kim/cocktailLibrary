package org.cocktail.cocktailappapi.domain.ingredient.validate;

import org.cocktail.common.error.ErrorCodeIfs;

public enum ErrorCodeIngredient implements ErrorCodeIfs {

    NULL_INGREDIENT(400,101,"요청한 재료의 정보가 없습니다."),
    EMPTY_INGREDIENT(400,102,"재료 리스트가 조회되지않았습니다."),
    DUPLICATE_INGREDIENT(400,103,"중복된 이름의 재료가 존재합니다.")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCodeIngredient(Integer httpStatusCode, Integer errorCode, String description) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.description = description;
    }

    @Override
    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
