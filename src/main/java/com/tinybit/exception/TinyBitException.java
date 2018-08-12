package com.tinybit.exception;

import com.tinybit.enums.ErrorEnum;

public class TinyBitException extends Exception {

    private ErrorEnum errorEnum;

    public TinyBitException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }
}
