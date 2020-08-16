package com.nnk.springboot.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Class for the RessourceAlreadyExistException.
 */
public class ResourceAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private int id;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getId() {
        return id;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param httpStatus the httpStatus
     *
     * @param message    the detail message
     *
     * @param ressource  the resource already existing
     */
    public ResourceAlreadyExistException(HttpStatus httpStatus, String message, int id) {
        super(message);
        this.httpStatus = httpStatus;
        this.id = id;
    }

}
