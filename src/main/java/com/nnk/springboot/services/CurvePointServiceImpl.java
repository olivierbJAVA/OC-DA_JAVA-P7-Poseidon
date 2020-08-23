package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class in charge of managing the services for CurvePoint entities.
 */
@Service
@Transactional
public class CurvePointServiceImpl implements ICurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    /**
     * Return all CurvePoints.
     *
     * @return The list of all CurvePoints
     */
    public List<CurvePoint> findAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    /**
     * Find a CurvePoint given its id.
     *
     * @param id The id of the CurvePoint
     * @return The CurvePoint corresponding to the id
     * A ResourceNotFoundException is thrown if no CurvePoint is found for the given id
     */
    @Override
    public CurvePoint findCurvePointById(Integer id) throws ResourceNotFoundException {
        return curvePointRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "CurvePoint"));
    }

    /**
     * Create a CurvePoint.
     *
     * @param curvePoint The CurvePoint to create
     * @return The CurvePoint created
     */
    @Override
    public CurvePoint createCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Update a CurvePoint.
     *
     * @param curvePoint The CurvePoint to update
     * @return The CurvePoint updated
     * A ResourceNotFoundException is thrown if the CurvePoint to update does not exist
     */
    @Override
    public CurvePoint updateCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.findById(curvePoint.getId()).orElseThrow(() -> new ResourceNotFoundException(curvePoint.getId(), "CurvePoint"));
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Delete a CurvePoint.
     *
     * @param id The id of the CurvePoint
     * A ResourceNotFoundException is thrown if the CurvePoint to delete does not exist
     */
    @Override
    public void deleteCurvePointById(Integer id) throws ResourceNotFoundException {
        curvePointRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "CurvePoint"));
        curvePointRepository.deleteById(id);
    }
}
