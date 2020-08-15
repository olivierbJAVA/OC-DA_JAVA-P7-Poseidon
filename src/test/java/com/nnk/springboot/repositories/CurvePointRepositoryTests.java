package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

/**
 * Class including integration (with the database) tests for the
 * CurvePoint Repository.
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class CurvePointRepositoryTests {

    @Autowired
    private CurvePointRepository curvePointRepositoryUnderTest;

    @Test
    public void saveCurvePoint() {
        // ARRANGE
        CurvePoint curvePointToSave = new CurvePoint(10, 10d, 30d);
        curvePointToSave.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToSave.setTerm(10d);
        curvePointToSave.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        // ACT
        CurvePoint curvePointSaved = curvePointRepositoryUnderTest.save(curvePointToSave);

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
        curvePointRepositoryUnderTest.save(curvePointToUpdate);

        // ACT
        curvePointToUpdate.setCurveId(20);
        CurvePoint curvePointUpdated = curvePointRepositoryUnderTest.save(curvePointToUpdate);

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
        curvePointToFind = curvePointRepositoryUnderTest.save(curvePointToFind);

        // ACT
        Optional<CurvePoint> curvePointFound = curvePointRepositoryUnderTest.findById(curvePointToFind.getId());

        // ASSERT
        assertTrue(curvePointFound.isPresent());
        assertEquals(curvePointToFind.getCurveId(), curvePointFound.get().getCurveId());
        assertEquals(curvePointToFind.getAsOfDate(), curvePointFound.get().getAsOfDate());
        assertEquals(curvePointToFind.getTerm(), curvePointFound.get().getTerm());
        assertEquals(curvePointToFind.getValue(), curvePointFound.get().getValue());
        assertEquals(curvePointToFind.getCreationDate(), curvePointFound.get().getCreationDate());
    }

    @Test
    public void findAllCurvePoints() {
        // ARRANGE
        CurvePoint curvePointToFind1 = new CurvePoint(10, 10d, 30d);
        curvePointToFind1.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind1.setTerm(10d);
        curvePointToFind1.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointRepositoryUnderTest.save(curvePointToFind1);

        CurvePoint curvePointToFind2 = new CurvePoint(10, 10d, 30d);
        curvePointToFind2.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind2.setTerm(10d);
        curvePointToFind2.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointRepositoryUnderTest.save(curvePointToFind2);

        CurvePoint curvePointToFind3 = new CurvePoint(10, 10d, 30d);
        curvePointToFind3.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToFind3.setTerm(10d);
        curvePointToFind3.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointRepositoryUnderTest.save(curvePointToFind3);

        List<CurvePoint> listCurvePoints = curvePointRepositoryUnderTest.findAll();
        assertTrue(listCurvePoints.size() == 3);
    }

    @Test
    public void deleteCurvePointById() {
        // ARRANGE
        CurvePoint curvePointToDelete = new CurvePoint(10, 10d, 30d);
        curvePointToDelete.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToDelete.setTerm(10d);
        curvePointToDelete.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointToDelete = curvePointRepositoryUnderTest.save(curvePointToDelete);

        // ACT
        Integer id = curvePointToDelete.getId();
        curvePointRepositoryUnderTest.deleteById(id);

        // ASSERT
        Optional<CurvePoint> curvePointDeleted = curvePointRepositoryUnderTest.findById(id);
        assertFalse(curvePointDeleted.isPresent());
    }
}