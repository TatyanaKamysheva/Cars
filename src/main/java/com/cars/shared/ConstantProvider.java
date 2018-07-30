package com.cars.shared;

public final class ConstantProvider {

    public static final String FIRST_NAME_PATTERN = "^[a-zA-Z]{3,15}$";
    public static final String LAST_NAME_PATTERN = "^[a-zA-Z]{3,15}$";
    public static final String PASSWORD_PATTERN = "^[a-zA-Z\\d]{3,20}$";
    public static final String NUMBER_PATTERN = "^[\\d]{1,15}$";
    public static final String PHONE_PATTERN = "^[a-zA-Z\\d\\D]{3,20}$";

    private ConstantProvider() {
    }
}