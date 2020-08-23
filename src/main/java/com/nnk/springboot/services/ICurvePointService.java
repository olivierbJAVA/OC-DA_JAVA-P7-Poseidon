package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * Interface to implement for managing the services for the CurvePoints.
 */
public interface ICurvePointService {

    /**
     * Return all CurvePoints.
     *
     * @return The list of all CurvePoints
     */
    List<CurvePoint> findAllCurvePoints();

    /**
     * Find a CurvePoint given its id.
     *
     * @param id The id of the CurvePoint
     * @return The CurvePoint corresponding to the id
     * A ResourceNotFoundException is thrown if no CurvePoint is found for the given id
     */
    CurvePoint findCurvePointById(Integer id) throws ResourceNotFoundException;

    /**
     * Create a CurvePoint.
     *
     * @param curvePoint The CurvePoint to create
     * @return The CurvePoint created
     */
    CurvePoint createCurvePoint(CurvePoint curvePoint);

    /**
     * Update a CurvePoint.
     *
     * @param curvePoint The CurvePoint to update
     * @return The CurvePoint updated
     * A ResourceNotFoundException is thrown if the CurvePoint to update does not exist
     */
    CurvePoint updateCurvePoint(CurvePoint curvePoint) throws ResourceNotFoundException;

    /**
     * Delete a CurvePoint.
     *
     * @param id The id of the CurvePoint
     * A ResourceNotFoundException is thrown if the CurvePoint to delete does not exist
     */
    void deleteCurvePointById(Integer id) throws ResourceNotFoundException;
}
