package com.weatherservice.project.utils;

import com.weatherservice.project.exception.BadRequestException;
import com.weatherservice.project.exception.FieldMessageException;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

import static com.weatherservice.project.common.FieldNames.EMAIL;
import static com.weatherservice.project.common.FieldNames.PASSWORD;
import static com.weatherservice.project.common.ResponseMessages.EMAIL_EMPTY_VALIDATION;
import static com.weatherservice.project.common.ResponseMessages.PASSWORD_DIGIT_VALIDATION;
import static com.weatherservice.project.common.ResponseMessages.PASSWORD_EMPTY_VALIDATION;
import static com.weatherservice.project.common.ResponseMessages.PASSWORD_LENGTH_VALIDATION;
import static com.weatherservice.project.common.ResponseMessages.PASSWORD_UPPERCASE_VALIDATION;

public class UserValidationUtils {
    private UserValidationUtils() {
    }

    private static final Pattern UPPERCASE = Pattern.compile(".*[A-Z].*");
    private static final Pattern DIGIT = Pattern.compile(".*\\d.*");


    public static void validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new FieldMessageException(PASSWORD, PASSWORD_EMPTY_VALIDATION);
        }

        if (password.length() < 8) {
            throw new FieldMessageException(PASSWORD, PASSWORD_LENGTH_VALIDATION);
        }

        if (!UPPERCASE.matcher(password).matches()) {
            throw new FieldMessageException(PASSWORD, PASSWORD_UPPERCASE_VALIDATION);
        }

        if (!DIGIT.matcher(password).matches()) {
            throw new FieldMessageException(PASSWORD, PASSWORD_DIGIT_VALIDATION);
        }
    }

    public static void validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new FieldMessageException(EMAIL, EMAIL_EMPTY_VALIDATION);
        }
    }

    // TODO: 01/05/23 validate user age

    public static void checkCondition(boolean isFail, String message) {
        checkCondition(isFail, new BadRequestException(message));
    }

    public static void checkCondition(boolean isFail, RuntimeException ex) {
        if (isFail) throw ex;
    }

}
