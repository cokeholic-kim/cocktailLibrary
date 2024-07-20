package org.cocktail.cocktailappapi.common;

import org.cocktail.common.error.ErrorCodeIfs;

public enum ErrorCodeFileSave implements ErrorCodeIfs {
    S3_SAVE_ERROR(500, 301, "파일 저장에 실패했습니다.");
    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCodeFileSave(Integer httpStatusCode, Integer errorCode, String description) {
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
