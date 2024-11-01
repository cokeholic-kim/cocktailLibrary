package org.cocktail.cocktailappapi.domain.user.validate;

import org.cocktail.common.error.ErrorCodeIfs;

public enum ErrorCodeUser implements ErrorCodeIfs {

    JOIN_ERROR(400,201,"가입 정보가 잘못입력되었습니다"),
    REDUNDANT_EMAIL(400,202,"사용중인 이메일입니다."),
    REDUNDANT_NICKNAME(400,203,"사용중인 닉네임 입니다."),
    NULL_USER(400,204,"없는 유저 입니다.")

    ;
    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCodeUser(Integer httpStatusCode, Integer errorCode, String description) {
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
