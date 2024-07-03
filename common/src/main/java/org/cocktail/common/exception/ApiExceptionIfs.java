package org.cocktail.common.exception;

import org.cocktail.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {
    ErrorCodeIfs errorcodeifs();

    String errorDescription();
}
