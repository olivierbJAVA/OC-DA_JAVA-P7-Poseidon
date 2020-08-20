package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurvePointServiceImpl implements ICurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    public List<CurvePoint> findAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint findCurvePointById(Integer id) throws ResourceNotFoundException {
        return curvePointRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id, "CurvePoint"));
    }

    @Override
    public CurvePoint createCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public CurvePoint updateCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.findById(curvePoint.getId()).orElseThrow(()-> new ResourceNotFoundException(curvePoint.getId(), "CurvePoint"));
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public void deleteCurvePointById(Integer id) throws ResourceNotFoundException {
        curvePointRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id, "CurvePoint"));
        curvePointRepository.deleteById(id);
    }
}
