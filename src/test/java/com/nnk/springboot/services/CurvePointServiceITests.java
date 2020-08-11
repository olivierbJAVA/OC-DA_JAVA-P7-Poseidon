package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the database) tests for the
 * CurvePoint Service.
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@Sql({"/schema-test.sql"})
public class CurvePointServiceITests {

    @Autowired
    private CurvePointImplService curvePointImplServiceUnderTest;

    @Test
    public void curvePointTests() {
        //CurvePoint curvePointTest = new CurvePoint(10, 10d, 30d);

        CurvePoint curvePointTest = new CurvePoint();
        curvePointTest.setCurveId(10);
        curvePointTest.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest.setTerm(10d);
        curvePointTest.setValue(30d);
        curvePointTest.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        // Save
        curvePointTest = curvePointImplServiceUnderTest.createCurvePoint(curvePointTest);
        assertNotNull(curvePointTest.getId());
        assertTrue(curvePointTest.getCurveId() == 10);

        // Update
        curvePointTest.setCurveId(20);
        curvePointTest = curvePointImplServiceUnderTest.updateCurvePoint(curvePointTest);
        assertTrue(curvePointTest.getCurveId() == 20);

        // Find by id
        CurvePoint curvePointGet = curvePointImplServiceUnderTest.findCurvePointById(curvePointTest.getId());
        assertNotNull(curvePointGet);
        assertEquals(curvePointTest.getCurveId(), curvePointGet.getCurveId());
        assertEquals(curvePointTest.getAsOfDate(), curvePointGet.getAsOfDate());
        assertEquals(curvePointTest.getTerm(), curvePointGet.getTerm());
        assertEquals(curvePointTest.getValue(), curvePointGet.getValue());
        assertEquals(curvePointTest.getCreationDate(), curvePointGet.getCreationDate());

        // Find all
        CurvePoint curvePointTest2 = new CurvePoint();
        curvePointTest2.setCurveId(10);
        curvePointTest2.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest2.setTerm(10d);
        curvePointTest2.setValue(30d);
        curvePointTest2.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointImplServiceUnderTest.createCurvePoint(curvePointTest2);

        CurvePoint curvePointTest3 = new CurvePoint();
        curvePointTest3.setCurveId(10);
        curvePointTest3.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest3.setTerm(10d);
        curvePointTest3.setValue(30d);
        curvePointTest3.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointImplServiceUnderTest.createCurvePoint(curvePointTest3);

        List<CurvePoint> listResult = curvePointImplServiceUnderTest.findAllCurvePoints();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = curvePointTest.getId();
        curvePointImplServiceUnderTest.deleteCurvePointById(id);
        assertThrows(RecordNotFoundException.class, () -> {
            curvePointImplServiceUnderTest.findCurvePointById(id);
        });
    }

}