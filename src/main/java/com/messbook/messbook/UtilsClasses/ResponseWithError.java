package com.messbook.messbook.UtilsClasses;

import com.messbook.messbook.Enums.Errors;

import java.util.List;

public class ResponseWithError<T, X> {
    private T response;
    private ErrorData<String> error = new ErrorData<String>();

    public ResponseWithError() {
        this.error.setErrorCode(Errors.SUCCESS);
    }

    public ResponseWithError(T response, ErrorData<String> error) {
        this.response = response;
        this.error = error;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public T getResponse(){
        return this.response;
    }

    public ErrorData<String> getError() {
        return this.error;
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

}
