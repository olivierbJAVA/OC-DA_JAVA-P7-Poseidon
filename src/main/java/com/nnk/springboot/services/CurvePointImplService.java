package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CurvePointImplService implements ICurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    public List<CurvePoint> getAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint getCurvePointById(Integer id) {
        return curvePointRepository.getOne(id);
    }

    @Override
    public CurvePoint saveOrUpdate(CurvePoint CurvePoint) {
        return curvePointRepository.save(CurvePoint);
    }

    @Override
    public void delete (Integer id) {
        curvePointRepository.deleteById(id);
    }
}
