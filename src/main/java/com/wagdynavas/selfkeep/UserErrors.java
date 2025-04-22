package com.wagdynavas.selfkeep;

import lombok.Getter;

@Getter
public enum UserErrors {

    UNMATCHED_USER_PASSWORD("UUP-400", "Password and confirmPassword does not match."),
    NOT_UNIQUE_USER_EMAIL("NUE-400", "Email already has an account registered."),
    EMAIL_NOT_FOUND("ENF-400", "Unable to find user with provided email.");

    private final String errorCode;

    private final String errorMessage;

    UserErrors(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
