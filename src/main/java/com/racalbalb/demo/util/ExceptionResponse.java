package com.racalbalb.demo.util;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    private String errorMessage;
    private String requestedURI;

    public ExceptionResponse(final String requestedURI, final String errorMessage) {
        this.errorMessage = errorMessage;
        this.requestedURI = requestedURI;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void callerURL(final String requestedURI) {
        this.requestedURI = requestedURI;
    }
}