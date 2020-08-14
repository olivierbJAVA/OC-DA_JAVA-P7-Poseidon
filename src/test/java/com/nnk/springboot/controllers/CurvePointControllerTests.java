package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.services.ICurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Timestamp.valueOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class including unit tests for the CurvePointController Class.
 */
@WebMvcTest(value = CurvePointController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class CurvePointControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(CurvePointControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICurvePointService mockCurvePointService;

    @BeforeEach
    private void setUpPerTest() {
    }

    // @RequestMapping(value = "/curvePoint/list")
    @Test
    public void home() {
        //ARRANGE
        CurvePoint curvePointTest1 = new CurvePoint();
        curvePointTest1.setCurveId(10);
        curvePointTest1.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest1.setTerm(10d);
        curvePointTest1.setValue(30d);
        curvePointTest1.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        CurvePoint curvePointTest2 = new CurvePoint();
        curvePointTest2.setCurveId(10);
        curvePointTest2.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest2.setTerm(10d);
        curvePointTest2.setValue(30d);
        curvePointTest2.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        CurvePoint curvePointTest3 = new CurvePoint();
        curvePointTest3.setCurveId(10);
        curvePointTest3.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest3.setTerm(10d);
        curvePointTest3.setValue(30d);
        curvePointTest3.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        List<CurvePoint> allCurvePointsToFind = new ArrayList<>();
        allCurvePointsToFind.add(curvePointTest1);
        allCurvePointsToFind.add(curvePointTest2);
        allCurvePointsToFind.add(curvePointTest3);

        doReturn(allCurvePointsToFind).when(mockCurvePointService).findAllCurvePoints();

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/curvePoint/list"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("curvePoints", allCurvePointsToFind))
                    .andExpect(view().name("curvePoint/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockCurvePointService, times(1)).findAllCurvePoints();
    }

    // @GetMapping(value = "/curvePoint/add"")
    @Test
    public void addCurvePointForm() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/curvePoint/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("curvePoint/add"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    // @PostMapping(value = "/curvePoint/validate"")
    @Test
    public void validate_whenNoError() {
        //ARRANGE
        CurvePoint curvePointTest = new CurvePoint();
        curvePointTest.setCurveId(10);
        curvePointTest.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest.setTerm(10d);
        curvePointTest.setValue(30d);
        curvePointTest.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        doReturn(curvePointTest).when(mockCurvePointService).createCurvePoint(curvePointTest);

        List<CurvePoint> listCurvePoints = new ArrayList<>();
        listCurvePoints.add(curvePointTest);

        doReturn(listCurvePoints).when(mockCurvePointService).findAllCurvePoints();

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/curvePoint/validate")
                    .param("curveId", "10")
                    .param("term", "10")
                    .param("value", "30"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/curvePoint/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockCurvePointService, times(1)).createCurvePoint(any(CurvePoint.class));
        verify(mockCurvePointService, times(1)).findAllCurvePoints();
    }

    // @PostMapping(value = "/curvePoint/validate"")
    @Test
    public void validate_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/curvePoint/update/1")
                    .param("curveId", "")
                    .param("term", "10")
                    .param("value", "30"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("curvePoint/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockCurvePointService, never()).createCurvePoint(any(CurvePoint.class));
        verify(mockCurvePointService, never()).findAllCurvePoints();
    }

    // @GetMapping(value = "/curvePoint/update/{id}"")
    @Test
    public void showUpdateForm() {
        //ARRANGE
        CurvePoint curvePointTest = new CurvePoint();
        curvePointTest.setCurveId(10);
        curvePointTest.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest.setTerm(10d);
        curvePointTest.setValue(30d);
        curvePointTest.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        doReturn(curvePointTest).when(mockCurvePointService).findCurvePointById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/curvePoint/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("curvePoint", curvePointTest))
                    .andExpect(view().name("curvePoint/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockCurvePointService, times(1)).findCurvePointById(1);
    }

    // @PostMapping(value = "/curvePoint/update/{id}"")
    @Test
    public void updateCurvePoint_whenNoError() {
        //ARRANGE
        CurvePoint curvePointTest = new CurvePoint();
        curvePointTest.setCurveId(10);
        curvePointTest.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest.setTerm(10d);
        curvePointTest.setValue(30d);
        curvePointTest.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        doReturn(curvePointTest).when(mockCurvePointService).updateCurvePoint(curvePointTest);
        //***
        doReturn(curvePointTest).when(mockCurvePointService).findCurvePointById(anyInt());

        List<CurvePoint> listCurvePoints = new ArrayList<>();
        listCurvePoints.add(curvePointTest);
        doReturn(listCurvePoints).when(mockCurvePointService).findAllCurvePoints();

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/curvePoint/update/1")
                    .param("curveId", "10")
                    .param("term", "10")
                    .param("value", "30"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/curvePoint/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockCurvePointService, times(1)).updateCurvePoint(any(CurvePoint.class));
        verify(mockCurvePointService, times(1)).findAllCurvePoints();
    }

    // @PostMapping(value = "/curvePoint/update/{id}"")
    @Test
    public void updateCurvePoint_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/curvePoint/update/1")
                    .param("curveId", "")
                    .param("term", "10")
                    .param("value", "30"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("curvePoint/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockCurvePointService, never()).updateCurvePoint(any(CurvePoint.class));
        verify(mockCurvePointService, never()).findAllCurvePoints();
    }

    // @GetMapping(value = "/curvePoint/delete/{id}"")
    @Test
    public void deleteCurvePoint_whenCurvePointExist() {
        //ARRANGE
        CurvePoint curvePointTest1 = new CurvePoint();
        curvePointTest1.setCurveId(10);
        curvePointTest1.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest1.setTerm(10d);
        curvePointTest1.setValue(30d);
        curvePointTest1.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        CurvePoint curvePointTest2 = new CurvePoint();
        curvePointTest2.setCurveId(10);
        curvePointTest2.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest2.setTerm(10d);
        curvePointTest2.setValue(30d);
        curvePointTest2.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        CurvePoint curvePointTest3 = new CurvePoint();
        curvePointTest3.setCurveId(10);
        curvePointTest3.setAsOfDate(valueOf("2020-08-10 10:20:30.0"));
        curvePointTest3.setTerm(10d);
        curvePointTest3.setValue(30d);
        curvePointTest3.setCreationDate(valueOf("2020-08-10 10:20:30.0"));

        List<CurvePoint> allCurvePointsToFind = new ArrayList<>();
        allCurvePointsToFind.add(curvePointTest1);
        allCurvePointsToFind.add(curvePointTest2);
        allCurvePointsToFind.add(curvePointTest3);

        doReturn(allCurvePointsToFind).when(mockCurvePointService).findAllCurvePoints();

        doNothing().when(mockCurvePointService).deleteCurvePointById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/curvePoint/delete/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/curvePoint/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockCurvePointService, times(1)).deleteCurvePointById(1);
        verify(mockCurvePointService, times(1)).findAllCurvePoints();
    }

    // @GetMapping(value = "/curvePoint/delete/{id}"")
    @Test
    public void deleteCurvePoint_whenCurvePointNotExist() {
        //ARRANGE
        doThrow(RecordNotFoundException.class).when(mockCurvePointService).deleteCurvePointById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/curvePoint/delete/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("errorRecordNotFound"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockCurvePointService, times(1)).deleteCurvePointById(1);
        verify(mockCurvePointService, never()).findAllCurvePoints();
    }

}