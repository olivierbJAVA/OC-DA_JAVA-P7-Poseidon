package com.nnk.springboot.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Class for the RessourceNotFoundException.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int resourceId;

    private String resourceType;

     /**
     * Constructs a new runtime exception.
     *
     * @param id  the resource not found
     */
    public ResourceNotFoundException(int resourceId, String resourceType) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }
}
