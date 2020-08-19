package com.nnk.springboot.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Class for the RessourceNotFoundException.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int id;

    public int getId() {
        return id;
    }

    /**
     * Constructs a new runtime exception.
     *
     * @param id  the resource not found
     */
    public ResourceNotFoundException(int id) {
        this.id = id;
    }
}
