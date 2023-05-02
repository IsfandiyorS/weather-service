package com.weatherservice.project.common;

public interface ResponseMessages {

    String AUTHORIZATION_FAILED = "Authorization failed";
    String PASSWORD_EMPTY_VALIDATION = "Password must not be empty";
    String PASSWORD_UPPERCASE_VALIDATION = "Password should have at least one uppercase letter";
    String PASSWORD_LENGTH_VALIDATION = "Password should have 8 or more symbols";
    String PASSWORD_DIGIT_VALIDATION = "Password should have at least one digit";
    String EMAIL_EMPTY_VALIDATION = "Email must not be empty";
    String USER_FIRSTNAME_EMPTY_VALIDATION = "User firstname must not be empty";
    String USER_LASTNAME_EMPTY_VALIDATION = "User lastname must not be empty";
    String USER_AGE_INCORRECT_VALIDATION = "User age must be greater than 0";
    String EMAIL_PATTERN_VALIDATION = "Email is not in valid format.";
    String CONFIRMATION_PASSWORD_VALIDATION = "Confirmation password does not match";
    String EMAIL_EXIST = "This email is already used on our platform";
    String RECORD_SUCCESSFULLY_CREATED = "Record is created";
    String OLD_PASSWORD_AND_NEW_PASSWORD_ARE_MATCHED = "New password shouldn't be same with old password";
    String USER_REGISTERED = "You can successfully register.";
    String OBJECT_NOT_FOUND = "%s not found";
    String USER_DELETED = "User successfully deleted.";
    String OBJECT_NOT_FOUND_BY_FIELD = "%s not found by this %s";
    String FIELD_VALIDATION_ERROR_MESSAGE = "You have to fill required fields as we expect.";
    String USER_HAS_NOT_ACCESS = "You don't have access for this action.";
    String USER_DOES_NOT_EXIST = "User not found";
    String USER_DOES_NOT_EXIST_BY_FIELD = "User not found by this %s";
    String INCORRECT_EMAIL_OR_PASSWORD = "Password or email is incorrect";
    String ENTER_VALID_PASSWORD = "Enter a valid password";
    String PASSWORD_CHANGED = "Password has been successfully changed";
    String TOKEN_NOT_FOUND = "Token not found!";
    String PASSWORD_RESTORED = "Password successfully restored!";
    String TOKEN_WITH_USER_ID_NOT_FOUND = "ConfirmationToken with this UserId was not found!";
    String USER_SUCCESSFULLY_CREATED = "User account successfully created.";
}
