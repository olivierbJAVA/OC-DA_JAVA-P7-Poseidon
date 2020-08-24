package com.nnk.springboot.exceptions;

/**
 * Class materializing the ResourceAlreadyExistException.
 */
public class ResourceAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String resourceId;

    private String resourceType;

    /**
     * Constructs a new ResourceAlreadyExistException.
     *
     * @param resourceId   the resourceId of the resource that already exists
     * @param resourceType the resourceType of the resource that already exists
     */
    public ResourceAlreadyExistException(String resourceId, String resourceType) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }
}
