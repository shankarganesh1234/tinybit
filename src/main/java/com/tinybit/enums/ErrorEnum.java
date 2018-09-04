package com.tinybit.enums;

public enum ErrorEnum {

    // TODO : Need to add proper error codes and messages
    INTERNAL_ERROR (10000, "An unexpected error occurred"),
    INVALID_KEY(10001, "Could not find any records with the given key"),
    EMPTY_DETAIL_REQUEST(10002, "Details are empty"),
    INVALID_DETAIL_REQUEST(10003, "Invalid details request"),
    COULD_NOT_CREATE_RECORD(10004, "Could not create record"),
    EMPTY_NAME(10005, "Empty name"),
    EMPTY_COIN_DETAILS(10006, "Empty coin details"),
    MAX_LENGTH_EXCEEDED(10007, "Attempting to add too many wallets");



    private Integer code;
    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}



