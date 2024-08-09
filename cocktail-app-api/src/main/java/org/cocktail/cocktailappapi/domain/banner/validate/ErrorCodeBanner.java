package org.cocktail.cocktailappapi.domain.banner.validate;

import org.cocktail.common.error.ErrorCodeIfs;

public enum ErrorCodeBanner implements ErrorCodeIfs {
    NULL_BANNER(400,201,"요청한 배너 정보가 없습니다."),
    EMPTY_BANNER(500,202,"배너 리스트가 조회되지않았습니다.")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCodeBanner(Integer httpStatusCode, Integer errorCode, String description) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.description = description;
    }
    ;

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
