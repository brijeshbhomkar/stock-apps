package com.stocks.data.service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * The error response code for the rest calls.
 */
@Data
public class ErrorResponse implements Serializable {

    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;

    public ErrorResponse(final HttpStatus httpStatus, final String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ErrorResponse(final HttpStatus httpStatus, final String message, final List<String> errors) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }
}
