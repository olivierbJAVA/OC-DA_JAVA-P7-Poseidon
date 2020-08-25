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
 * CurvePoint entity Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql("/schema-test.sql")
public class CurvePointServiceImplITests {

    @Autowired
    private CurvePointServiceImpl curvePointServiceImplUnderTest;

    @Test
    @Sql("/cleandb-test.sql")
    public void createCurvePoint() {
        // ARRANGE
        CurvePoint curvePointToCreate = new CurvePoint(10, 10d, 30d);
        curvePointToCreate.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToCreate.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        // ACT
        CurvePoint curvePointCreated = curvePointServiceImplUnderTest.createCurvePoint(curvePointToCreate);

        // ASSERT
        assertNotNull(curvePointCreated.getId());
        assertEquals(curvePointToCreate.getCurveId(), curvePointCreated.getCurveId());
        assertEquals(curvePointToCreate.getAsOfDate(), curvePointCreated.getAsOfDate());
        assertEquals(curvePointToCreate.getTerm(), curvePointCreated.getTerm());
        assertEquals(curvePointToCreate.getValue(), curvePointCreated.getValue());
        assertEquals(curvePointToCreate.getCreationDate(), curvePointCreated.getCreationDate());

    }

    @Test
    @Sql("/cleandb-test.sql")
    public void updateCurvePoint() {
        // ARRANGE
        CurvePoint curvePointToUpdate = new CurvePoint(10, 10d, 30d);
        curvePointToUpdate.setAsOfDate(valueOf("2020-08-10 11:20:30.0"));
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
    @Sql("/cleandb-test.sql")
    public void findCurvePointById() {
        // ARRANGE
        CurvePoint curvePointToFind = new CurvePoint(10, 10d, 30d);
        curvePointToFind.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
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
    @Sql("/cleandb-test.sql")
    public void findAllCurvePoints() {
        // ARRANGE
        CurvePoint curvePointToFind1 = new CurvePoint(10, 10d, 30d);
        curvePointToFind1.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind1.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointServiceImplUnderTest.createCurvePoint(curvePointToFind1);

        CurvePoint curvePointToFind2 = new CurvePoint(10, 10d, 30d);
        curvePointToFind2.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind2.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointServiceImplUnderTest.createCurvePoint(curvePointToFind2);

        CurvePoint curvePointToFind3 = new CurvePoint(10, 10d, 30d);
        curvePointToFind3.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind3.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointServiceImplUnderTest.createCurvePoint(curvePointToFind3);

        List<CurvePoint> listCurvePoints = curvePointServiceImplUnderTest.findAllCurvePoints();
        assertTrue(listCurvePoints.size() == 3);
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void deleteCurvePointById() {
        // ARRANGE
        CurvePoint curvePointToDelete = new CurvePoint(10, 10d, 30d);
        curvePointToDelete.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
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