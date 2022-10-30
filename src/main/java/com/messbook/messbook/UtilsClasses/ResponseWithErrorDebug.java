package com.messbook.messbook.UtilsClasses;

import com.messbook.messbook.Enums.Errors;

import java.util.List;

public class ResponseWithErrorDebug<T, X> {
    private T response;
    private ErrorData<String> error = new ErrorData<>();

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public ErrorData<String> getError() {
        return error;
    }

    public void setError(ErrorData<String> error) {
        this.error = error;
    }

    public void configError(String errorCode, String ...messages) {
        this.error.setErrorCode(errorCode);
        for(String message : messages) {
            this.error.getErrorMessages().add(message);
        }
    }

    public void config(T response, String errorCode, String ...messages) {
        this.configError(errorCode, messages);
        this.setResponse(response);
    }

    public void configAsFailed(String ...messages) {
        this.setResponse(null);
        this.error.setErrorCode(Errors.FAILED);
        for(String message : messages) {
            this.error.getErrorMessages().add(message);
        }
    }

    public void configAsFailed(List<String> messages) {
        this.setResponse(null);
        this.error.setErrorCode(Errors.FAILED);
        for(String message : messages) {
            this.error.getErrorMessages().add(message);
        }
    }

    public String retrieve() {
        return this.error.getErrorCode();
    }
}
