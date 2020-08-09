package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface ICurvePointService {

    List<CurvePoint> getAllCurvePoints();

    CurvePoint getCurvePointById(Integer id);

    CurvePoint saveCurvePoint(CurvePoint curvePoint);

    CurvePoint updateCurvePoint(CurvePoint curvePoint);

    void deleteCurvePoint(Integer id);
}
