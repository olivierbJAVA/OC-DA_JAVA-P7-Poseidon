package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class CurvePointServiceITests {

    @Autowired
    private CurvePointRepository curvePointRepositoryUnderTest;

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
        curvePointTest = curvePointRepositoryUnderTest.save(curvePointTest);
        assertNotNull(curvePointTest.getId());
        assertTrue(curvePointTest.getCurveId() == 10);

        // Update
        curvePointTest.setCurveId(20);
        curvePointTest = curvePointRepositoryUnderTest.save(curvePointTest);
        assertTrue(curvePointTest.getCurveId() == 20);

        // Find by id
        Optional<CurvePoint> curvePointGet = curvePointRepositoryUnderTest.findById(curvePointTest.getId());
        assertTrue(curvePointGet.isPresent());
        assertEquals(curvePointTest.getCurveId(), curvePointGet.get().getCurveId());
        assertEquals(curvePointTest.getAsOfDate(), curvePointGet.get().getAsOfDate());
        assertEquals(curvePointTest.getTerm(), curvePointGet.get().getTerm());
        assertEquals(curvePointTest.getValue(), curvePointGet.get().getValue());
        assertEquals(curvePointTest.getCreationDate(), curvePointGet.get().getCreationDate());

        // Find all
        CurvePoint curvePointTest2 = new CurvePoint();
        curvePointTest2.setCurveId(10);
        curvePointTest2.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest2.setTerm(10d);
        curvePointTest2.setValue(30d);
        curvePointTest2.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointRepositoryUnderTest.save(curvePointTest2);

        CurvePoint curvePointTest3 = new CurvePoint();
        curvePointTest3.setCurveId(10);
        curvePointTest3.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest3.setTerm(10d);
        curvePointTest3.setValue(30d);
        curvePointTest3.setCreationDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointRepositoryUnderTest.save(curvePointTest3);

        List<CurvePoint> listResult = curvePointRepositoryUnderTest.findAll();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = curvePointTest.getId();
        curvePointRepositoryUnderTest.delete(curvePointTest);
        Optional<CurvePoint> curvePointList = curvePointRepositoryUnderTest.findById(id);
        assertFalse(curvePointList.isPresent());
    }

}