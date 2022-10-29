package com.messbook.messbook.UtilsClasses;

public class ResponseWithError<T, X> {
    private T response;
    private ErrorData<X> error;

    public ResponseWithError() {}

    public ResponseWithError(T response, ErrorData<X> error) {
        this.response = response;
        this.error = error;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public T getResponse(){
        return this.response;
    }

    public ErrorData<X> getError() {
        return this.error;
    }

    public void configError(X errorCode, String ...messages) {
        this.error.setErrorCode(errorCode);
        for(String message : messages) {
            this.error.getErrorMessages().add(message);
        }
    }
}
