package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.RecordNotFoundException;

import java.util.List;

public interface ICurvePointService {

    List<CurvePoint> findAllCurvePoints();

    CurvePoint findCurvePointById(Integer id) throws RecordNotFoundException;

    CurvePoint createCurvePoint(CurvePoint curvePoint);

    CurvePoint updateCurvePoint(CurvePoint curvePoint);

    void deleteCurvePoint(Integer id) throws RecordNotFoundException;
}
