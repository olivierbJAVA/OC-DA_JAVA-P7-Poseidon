package com.nnk.springboot.exceptions;

/**
 * Class for the ResourceAlreadyExistException.
 */
public class ResourceAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String username;

    public String getUsername() {
        return username;
    }

    /**
     * Constructs a new runtime exception.
     *
     * @param username  the resource that already exists
     */
    public ResourceAlreadyExistException(String username) {
        this.username = username;
    }
}
