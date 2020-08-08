package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface ICurvePointService {

    List<CurvePoint> getAllCurvePoints();

    CurvePoint getCurvePointById(Integer id);

    CurvePoint saveOrUpdate(CurvePoint curvePoint);

    void delete (Integer id);

}
