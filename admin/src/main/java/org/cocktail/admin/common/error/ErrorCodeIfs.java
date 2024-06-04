package org.cocktail.admin.common.error;

public interface ErrorCodeIfs {
    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
