package com.ihminq.movie_hub.domain.exception;

import com.ihminq.movie_hub.domain.utils.ValidateResult;

public class ValidationException extends Exception{
    private final ValidateResult mResult;

    public ValidationException(ValidateResult result) {
        super(result.getMessage());
        this.mResult = result;
    }

    public ValidateResult getResult() {
        return mResult;
    }
}
