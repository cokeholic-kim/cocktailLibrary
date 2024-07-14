package org.cocktail.cocktailappapi.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.cocktail.common.api.Api;
import org.cocktail.common.error.ErrorCodeIfs;
import org.cocktail.common.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(1)
public class ApiExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity apiException(ApiException apiException) {
        log.error("", apiException);

        ErrorCodeIfs errorCode = apiException.getErrorCodeIfs();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(
                        Api.ERROR(errorCode, apiException.getErrorDescription())
                );
    }
}
