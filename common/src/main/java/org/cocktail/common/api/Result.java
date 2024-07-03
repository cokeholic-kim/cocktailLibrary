package org.cocktail.common.api;


import org.cocktail.common.error.ErrorCodeIfs;

public class Result {
    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    private Result(Integer resultCode, String resultMessage, String resultDescription) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultDescription = resultDescription;
    }

    public static Result OK(Integer statusCode,String message) {
        return new Result(
                statusCode,
                message,
                "성공"
        );
    }

    public static Result OK() {
        return new Result(
                200,
                "서버와 정상적으로 연결되었습니다.",
                "성공"
        );
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs) {
        return new Result(
                errorCodeIfs.getErrorCode(),
                errorCodeIfs.getDescription(),
                "에러발생"
        );
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, Throwable throwable) {
        return new Result(
                errorCodeIfs.getErrorCode(),
                errorCodeIfs.getDescription(),
                throwable.getLocalizedMessage()
        );
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String description) {
        return new Result(
                errorCodeIfs.getErrorCode(),
                errorCodeIfs.getDescription(),
                description
        );
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public String getResultDescription() {
        return resultDescription;
    }
}
