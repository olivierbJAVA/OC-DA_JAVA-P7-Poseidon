package com.nnk.springboot.exceptions;

/**
 * Class materializing the ResourceNotFoundException.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int resourceId;

    private final String resourceType;

    /**
     * Constructs a new ResourceNotFoundException.
     *
     * @param resourceId   the resourceId of the resource that is not found
     * @param resourceType the resourceType of the resource that is not found
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
