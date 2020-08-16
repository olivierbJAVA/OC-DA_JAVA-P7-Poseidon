package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the repository layer) tests for the
 * CurvePoint Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql({"/schema-test.sql"})
public class CurvePointServiceImplITests {

    @Autowired
    private CurvePointServiceImpl curvePointServiceImplUnderTest;

    @Test
    public void createCurvePoint() {
        // ARRANGE
        CurvePoint curvePointToSave = new CurvePoint(10, 10d, 30d);
        curvePointToSave.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToSave.setTerm(10d);
        curvePointToSave.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        // ACT
        CurvePoint curvePointSaved = curvePointServiceImplUnderTest.createCurvePoint(curvePointToSave);

        // ASSERT
        assertNotNull(curvePointSaved.getId());
        assertEquals(curvePointToSave.getCurveId(), curvePointSaved.getCurveId());
        assertEquals(curvePointToSave.getAsOfDate(), curvePointSaved.getAsOfDate());
        assertEquals(curvePointToSave.getTerm(), curvePointSaved.getTerm());
        assertEquals(curvePointToSave.getValue(), curvePointSaved.getValue());
        assertEquals(curvePointToSave.getCreationDate(), curvePointSaved.getCreationDate());

    }

    @Test
    public void updateCurvePoint() {
        // ARRANGE
        CurvePoint curvePointToUpdate = new CurvePoint(10, 10d, 30d);
        curvePointToUpdate.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToUpdate.setTerm(10d);
        curvePointToUpdate.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointServiceImplUnderTest.createCurvePoint(curvePointToUpdate);

        // ACT
        curvePointToUpdate.setCurveId(20);
        CurvePoint curvePointUpdated = curvePointServiceImplUnderTest.updateCurvePoint(curvePointToUpdate);

        // ASSERT
        assertEquals(curvePointToUpdate.getCurveId(), curvePointUpdated.getCurveId());
        assertEquals(curvePointToUpdate.getAsOfDate(), curvePointUpdated.getAsOfDate());
        assertEquals(curvePointToUpdate.getTerm(), curvePointUpdated.getTerm());
        assertEquals(curvePointToUpdate.getValue(), curvePointUpdated.getValue());
        assertEquals(curvePointToUpdate.getCreationDate(), curvePointUpdated.getCreationDate());
    }

    @Test
    public void findCurvePointById() {
        // ARRANGE
        CurvePoint curvePointToFind = new CurvePoint(10, 10d, 30d);
        curvePointToFind.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind.setTerm(10d);
        curvePointToFind.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind = curvePointServiceImplUnderTest.createCurvePoint(curvePointToFind);

        // ACT
        CurvePoint curvePointFound = curvePointServiceImplUnderTest.findCurvePointById(curvePointToFind.getId());

        // ASSERT
        assertNotNull(curvePointFound);
        assertEquals(curvePointToFind.getCurveId(), curvePointFound.getCurveId());
        assertEquals(curvePointToFind.getAsOfDate(), curvePointFound.getAsOfDate());
        assertEquals(curvePointToFind.getTerm(), curvePointFound.getTerm());
        assertEquals(curvePointToFind.getValue(), curvePointFound.getValue());
        assertEquals(curvePointToFind.getCreationDate(), curvePointFound.getCreationDate());
    }

    @Test
    public void findAllCurvePoints() {
        // ARRANGE
        CurvePoint curvePointToFind1 = new CurvePoint(10, 10d, 30d);
        curvePointToFind1.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind1.setTerm(10d);
        curvePointToFind1.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointServiceImplUnderTest.createCurvePoint(curvePointToFind1);

        CurvePoint curvePointToFind2 = new CurvePoint(10, 10d, 30d);
        curvePointToFind2.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind2.setTerm(10d);
        curvePointToFind2.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointServiceImplUnderTest.createCurvePoint(curvePointToFind2);

        CurvePoint curvePointToFind3 = new CurvePoint(10, 10d, 30d);
        curvePointToFind3.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind3.setTerm(10d);
        curvePointToFind3.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointServiceImplUnderTest.createCurvePoint(curvePointToFind3);

        List<CurvePoint> listCurvePoints = curvePointServiceImplUnderTest.findAllCurvePoints();
        assertTrue(listCurvePoints.size() == 3);
    }

    @Test
    public void deleteCurvePointById() {
        // ARRANGE
        CurvePoint curvePointToDelete = new CurvePoint(10, 10d, 30d);
        curvePointToDelete.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToDelete.setTerm(10d);
        curvePointToDelete.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToDelete = curvePointServiceImplUnderTest.createCurvePoint(curvePointToDelete);

        // ACT
        Integer id = curvePointToDelete.getId();
        curvePointServiceImplUnderTest.deleteCurvePointById(id);

        // ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            curvePointServiceImplUnderTest.findCurvePointById(id);
        });
    }
}