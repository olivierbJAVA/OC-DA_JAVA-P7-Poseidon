package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class including unit tests for the CurvePointServiceImpl Class.
 */
@ExtendWith(MockitoExtension.class)
public class CurvePointServiceImplTests {

    @InjectMocks
    private CurvePointServiceImpl curvePointServiceImplUnderTest;

    @Mock
    private CurvePointRepository mockCurvePointRepository;

    @Test
    public void createCurvePoint() {
        // ARRANGE
        CurvePoint curvePointToCreate = new CurvePoint(10, 10d, 30d);
        curvePointToCreate.setCurveId(1);
        curvePointToCreate.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToCreate.setTerm(10d);
        curvePointToCreate.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        doReturn(curvePointToCreate).when(mockCurvePointRepository).save(curvePointToCreate);

        // ACT
        CurvePoint curvePointCreated = curvePointServiceImplUnderTest.createCurvePoint(curvePointToCreate);

        // ASSERT
        verify(mockCurvePointRepository, times(1)).save(curvePointToCreate);
        assertEquals(curvePointToCreate, curvePointCreated);
    }

    @Test
    public void updateCurvePoint_whenIdExist() {
        // ARRANGE
        CurvePoint curvePointToUpdate = new CurvePoint(10, 10d, 30d);
        curvePointToUpdate.setCurveId(1);
        curvePointToUpdate.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToUpdate.setTerm(10d);
        curvePointToUpdate.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        doReturn(Optional.of(curvePointToUpdate)).when(mockCurvePointRepository).findById(curvePointToUpdate.getId());
        doReturn(curvePointToUpdate).when(mockCurvePointRepository).save(curvePointToUpdate);

        // ACT
        CurvePoint curvePointUpdated = curvePointServiceImplUnderTest.updateCurvePoint(curvePointToUpdate);

        // ASSERT
        verify(mockCurvePointRepository, times(1)).save(curvePointToUpdate);
        assertEquals(curvePointToUpdate, curvePointUpdated);
    }

    @Test
    public void updateCurvePoint_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockCurvePointRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            curvePointServiceImplUnderTest.findCurvePointById(1);
        });
        verify(mockCurvePointRepository, never()).save(any(CurvePoint.class));
    }

    @Test
    public void findCurvePointById_whenIdExist() {
        // ARRANGE
        CurvePoint curvePointToFind = new CurvePoint(10, 10d, 30d);
        curvePointToFind.setId(1);
        curvePointToFind.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind.setTerm(10d);
        curvePointToFind.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        doReturn(Optional.of(curvePointToFind)).when(mockCurvePointRepository).findById(curvePointToFind.getId());

        // ACT
        CurvePoint curvePointFound = curvePointServiceImplUnderTest.findCurvePointById(curvePointToFind.getId());

        // ASSERT
        verify(mockCurvePointRepository, times(1)).findById(curvePointToFind.getId());
        assertEquals(curvePointToFind, curvePointFound);
    }

    @Test
    public void findCurvePointById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockCurvePointRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            curvePointServiceImplUnderTest.findCurvePointById(1);
        });
        verify(mockCurvePointRepository, times(1)).findById(1);
    }

    @Test
    public void findAllCurvePoints() {
        // ARRANGE
        CurvePoint curvePointToFind1 = new CurvePoint(10, 10d, 30d);
        curvePointToFind1.setCurveId(1);
        curvePointToFind1.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind1.setTerm(10d);
        curvePointToFind1.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        CurvePoint curvePointToFind2 = new CurvePoint(10, 10d, 30d);
        curvePointToFind1.setCurveId(2);
        curvePointToFind2.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind2.setTerm(10d);
        curvePointToFind2.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        CurvePoint curvePointToFind3 = new CurvePoint(10, 10d, 30d);
        curvePointToFind1.setCurveId(3);
        curvePointToFind3.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind3.setTerm(10d);
        curvePointToFind3.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        List<CurvePoint> listCurvePointsToFind = new ArrayList<>();
        listCurvePointsToFind.add(curvePointToFind1);
        listCurvePointsToFind.add(curvePointToFind2);
        listCurvePointsToFind.add(curvePointToFind3);

        doReturn(listCurvePointsToFind).when(mockCurvePointRepository).findAll();

        // ACT
        List<CurvePoint> listCurvePointsFound = curvePointServiceImplUnderTest.findAllCurvePoints();

        // ASSERT
        verify(mockCurvePointRepository, times(1)).findAll();
        assertEquals(listCurvePointsToFind, listCurvePointsFound);
    }

    @Test
    public void deleteCurvePointById_whenIdExist() {
        // ARRANGE
        CurvePoint curvePointToDelete = new CurvePoint(10, 10d, 30d);
        curvePointToDelete.setId(1);
        curvePointToDelete.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToDelete.setTerm(10d);
        curvePointToDelete.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        doReturn(Optional.of(curvePointToDelete)).when(mockCurvePointRepository).findById(curvePointToDelete.getId());

        // ACT
        curvePointServiceImplUnderTest.deleteCurvePointById(curvePointToDelete.getId());

        // ASSERT
        verify(mockCurvePointRepository, times(1)).deleteById(curvePointToDelete.getId());
    }

    @Test
    public void deleteCurvePointById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockCurvePointRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            curvePointServiceImplUnderTest.deleteCurvePointById(1);
        });
        verify(mockCurvePointRepository, times(1)).findById(1);
        verify(mockCurvePointRepository, never()).deleteById(1);
    }
}
