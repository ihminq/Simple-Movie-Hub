package com.ihminq.movie_hub.domain.utils;

public class ValidateResult {
    private final boolean isValid;
    private final String message;
    private final Field errorField;

    public enum Field {
        EMAIL,
        PASSWORD,
        FULL_NAME,
        NONE
    }

    public ValidateResult(boolean isValid, String message) {
        this(isValid, message, Field.NONE);
    }

    public ValidateResult(boolean isValid, String message, Field errorField) {
        this.isValid = isValid;
        this.message = message;
        this.errorField = errorField;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }

    public Field getErrorField() {
        return errorField;
    }
}
