package org.cocktail.admin.common;

import org.cocktail.common.error.ErrorCodeIfs;

public enum ErrorCodeFile implements ErrorCodeIfs {
    FAIL_SAVE(500,201,"파일 저장에 실패했습니다.")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCodeFile(Integer httpStatusCode, Integer errorCode, String description) {
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
