package com.ihminq.movie_hub.domain.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final ValidateResult EMAIL_VALID = new ValidateResult(true, "Email is valid");
    private static final ValidateResult PASSWORD_VALID = new ValidateResult(true, "Password is valid");
    private static final ValidateResult FULLNAME_VALID = new ValidateResult(true, "Fullname is valid");

    public static ValidateResult isEmailValid(String email) {
        ValidateResult result = checkEmailEmpty(email);
        if (!result.isValid()) return new ValidateResult(false, result.getMessage(), ValidateResult.Field.EMAIL);

        result = checkEmailFormat(email);
        if (!result.isValid()) return new ValidateResult(false, result.getMessage(), ValidateResult.Field.EMAIL);

        return EMAIL_VALID;
    }

    private static ValidateResult checkEmailEmpty(String email) {
        if (email == null || email.trim().isEmpty()) {
            return new ValidateResult(false, "Email cannot be empty");
        }
        return new ValidateResult(true, "");
    }

    private static ValidateResult checkEmailFormat(String email) {
        String emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            return new ValidateResult(false, "Email format is incorrect", ValidateResult.Field.EMAIL);
        }
        return new ValidateResult(true, "");
    }

    public static ValidateResult isPasswordValid(String password) {
        ValidateResult result = checkPasswordLength(password);
        if (!result.isValid()) return new ValidateResult(false, result.getMessage(), ValidateResult.Field.PASSWORD);

        result = checkPasswordUppercase(password);
        if (!result.isValid()) return new ValidateResult(false, result.getMessage(), ValidateResult.Field.PASSWORD);

        result = checkPasswordSpecialChar(password);
        if (!result.isValid()) return new ValidateResult(false, result.getMessage(), ValidateResult.Field.PASSWORD);

        return PASSWORD_VALID;
    }

    public static ValidateResult isFullnameValid(String fullname) {
        if (fullname == null || fullname.trim().isEmpty()) {
            return new ValidateResult(false, "Fullname cannot be empty", ValidateResult.Field.FULL_NAME);
        }

        if (fullname.length() < 5 || fullname.length() > 20) {
            return new ValidateResult(false, "Fullname must has 6-20 characters", ValidateResult.Field.FULL_NAME);
        }

        return FULLNAME_VALID;
    }


    private static ValidateResult checkPasswordLength(String password) {
        if (password == null || password.length() < 8) {
            return new ValidateResult(false, "Password must be at least 8 characters", ValidateResult.Field.PASSWORD);
        }
        return new ValidateResult(true, "");
    }

    private static ValidateResult checkPasswordUppercase(String password) {
        if (!password.matches(".*[A-Z].*")) {
            return new ValidateResult(false, "Password must contain at least one uppercase letter", ValidateResult.Field.PASSWORD);
        }
        return new ValidateResult(true, "");
    }

    private static ValidateResult checkPasswordSpecialChar(String password) {
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            return new ValidateResult(false, "Password must contain at least one special character", ValidateResult.Field.PASSWORD);
        }
        return new ValidateResult(true, "");
    }
}
