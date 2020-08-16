package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICurvePointService {

    List<CurvePoint> findAllCurvePoints();

    CurvePoint findCurvePointById(Integer id) throws ResourceNotFoundException;

    CurvePoint createCurvePoint(CurvePoint curvePoint);

    CurvePoint updateCurvePoint(CurvePoint curvePoint);

    void deleteCurvePointById(Integer id) throws ResourceNotFoundException;
}
