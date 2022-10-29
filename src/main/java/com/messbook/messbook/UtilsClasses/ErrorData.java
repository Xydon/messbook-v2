package com.messbook.messbook.UtilsClasses;

import java.util.LinkedList;

public class ErrorData<T> {
    private T ErrorCode;
    private LinkedList<String> ErrorMessages = new LinkedList<String>();

    public ErrorData() {}

    public ErrorData(T errorCode, LinkedList<String> errorMessages) {
        ErrorCode = errorCode;
        ErrorMessages = errorMessages;
    }

    public T getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(T errorCode) {
        ErrorCode = errorCode;
    }

    public LinkedList<String> getErrorMessages() {
        return ErrorMessages;
    }

    public void setErrorMessages(LinkedList<String> errorMessages) {
        ErrorMessages = errorMessages;
    }
}
