package org.cocktail.cocktailappapi.domain.comment.validate;

import org.cocktail.common.error.ErrorCodeIfs;

public enum ErrorCodeComment implements ErrorCodeIfs {

    NULL_USER(400,300,"요청한 유저의 정보가 없습니다."),
    NULL_COMMENT(400,302,"요청한 댓글 정보가 없습니다."),
    FAIL_DELETE(400,303,"작성자가 다른 댓글은 삭제할수 없습니다.")

    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    ErrorCodeComment(Integer httpStatusCode, Integer errorCode, String description) {
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
