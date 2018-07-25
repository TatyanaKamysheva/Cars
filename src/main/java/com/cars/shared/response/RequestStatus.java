package com.cars.shared.response;

public enum RequestStatus {
    SUCCESS("Success"),
    USER_NOT_FOUND_ERROR("User not found error"),
    ILLEGAL_ARGUMENT_ERROR("The instance is not an entity"),
    SQL_ERROR("SQL error"),
    ERROR("Error");

    private final String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
