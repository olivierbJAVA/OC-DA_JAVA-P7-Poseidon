package com.nnk.springboot.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.services.IRatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.View;

/**
 * Class including unit tests for the RatingController Class.
 */
@WebMvcTest(value = RatingController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
//@AutoConfigureMockMvc(addFilters = false)
//@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class RatingControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(RatingControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRatingService mockRatingService;

    @BeforeEach
    private void setUpPerTest() {
    }

    // @RequestMapping(value = "/rating/list")
    @Test
    public void home() {
        //ARRANGE
        Rating ratingTest1 = new Rating();
        ratingTest1.setMoodysRating("Moodys Rating");
        ratingTest1.setSandPRating("Sand PRating");
        ratingTest1.setFitchRating("Fitch Rating");
        ratingTest1.setOrderNumber(10);

        Rating ratingTest2 = new Rating();
        ratingTest2.setMoodysRating("Moodys Rating");
        ratingTest2.setSandPRating("Sand PRating");
        ratingTest2.setFitchRating("Fitch Rating");
        ratingTest2.setOrderNumber(20);

        Rating ratingTest3 = new Rating();
        ratingTest3.setMoodysRating("Moodys Rating");
        ratingTest3.setSandPRating("Sand PRating");
        ratingTest3.setFitchRating("Fitch Rating");
        ratingTest3.setOrderNumber(30);

        List<Rating> allRatingsToFind = new ArrayList<>();
        allRatingsToFind.add(ratingTest1);
        allRatingsToFind.add(ratingTest2);
        allRatingsToFind.add(ratingTest3);

        doReturn(allRatingsToFind).when(mockRatingService).findAllRatings();

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/rating/list"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("ratings", allRatingsToFind))
                    .andExpect(view().name("rating/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, times(1)).findAllRatings();
    }

    // @GetMapping(value = "/rating/add"")
    @Test
    public void addRatingForm() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/rating/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("rating/add"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    // @PostMapping(value = "/rating/validate"")
    @Test
    public void validate_whenNoError() {
        //ARRANGE
        Rating ratingTest = new Rating();
        ratingTest.setId(1);
        ratingTest.setMoodysRating("Moodys Rating");
        ratingTest.setSandPRating("Sand PRating");
        ratingTest.setFitchRating("Fitch Rating");
        ratingTest.setOrderNumber(10);

        doReturn(ratingTest).when(mockRatingService).createRating(ratingTest);

        List<Rating> listRatings = new ArrayList<>();
        listRatings.add(ratingTest);

        doReturn(listRatings).when(mockRatingService).findAllRatings();

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/rating/validate")
                    .param("moodysRating", "MoodysRating")
                    .param("sandPRating", "Sand PRating")
                    .param("fitchRating", "Fitch Rating")
                    .param("orderNumber", "10"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/rating/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, times(1)).createRating(any(Rating.class));
        verify(mockRatingService, times(1)).findAllRatings();
    }

    // @PostMapping(value = "/rating/validate"")
    @Test
    public void validate_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/rating/update/1")
                    .param("moodysRating", "")
                    .param("sandPRating", "Sand PRating")
                    .param("fitchRating", "Fitch Rating")
                    .param("orderNumber", "10"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("rating/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, never()).createRating(any(Rating.class));
        verify(mockRatingService, never()).findAllRatings();
    }

    // @GetMapping(value = "/rating/update/{id}"")
    @Test
    public void showUpdateForm() {
        //ARRANGE
        Rating ratingTest = new Rating();
        ratingTest.setId(1);
        ratingTest.setMoodysRating("Moodys Rating");
        ratingTest.setSandPRating("Sand PRating");
        ratingTest.setFitchRating("Fitch Rating");
        ratingTest.setOrderNumber(10);

        doReturn(ratingTest).when(mockRatingService).findRatingById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/rating/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("rating", ratingTest))
                    .andExpect(view().name("rating/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, times(1)).findRatingById(1);
    }

    // @PostMapping(value = "/rating/update/{id}"")
    @Test
    public void updateRating_whenNoError() {
        //ARRANGE
        Rating ratingTest = new Rating();
        ratingTest.setId(1);
        ratingTest.setMoodysRating("Moodys Rating");
        ratingTest.setSandPRating("Sand PRating");
        ratingTest.setFitchRating("Fitch Rating");
        ratingTest.setOrderNumber(10);

        doReturn(ratingTest).when(mockRatingService).updateRating(ratingTest);

        List<Rating> listRatings = new ArrayList<>();
        listRatings.add(ratingTest);
        doReturn(listRatings).when(mockRatingService).findAllRatings();

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/rating/update/1")
                    .param("moodysRating", "Moodys Rating")
                    .param("sandPRating", "Sand PRating")
                    .param("fitchRating", "Fitch Rating")
                    .param("orderNumber", "10"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/rating/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, times(1)).updateRating(any(Rating.class));
        verify(mockRatingService, times(1)).findAllRatings();
    }

    // @PostMapping(value = "/rating/update/{id}"")
    @Test
    public void updateRating_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/rating/update/1")
                    .param("moodysRating", "")
                    .param("sandPRating", "Sand PRating")
                    .param("fitchRating", "Fitch Rating")
                    .param("orderNumber", "10"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("rating/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, never()).updateRating(any(Rating.class));
        verify(mockRatingService, never()).findAllRatings();
    }

    // @GetMapping(value = "/rating/delete/{id}"")
    @Test
    public void deleteRating_whenRatingExist() {
        //ARRANGE
        Rating ratingTest1 = new Rating();
        ratingTest1.setMoodysRating("Moodys Rating");
        ratingTest1.setSandPRating("Sand PRating");
        ratingTest1.setFitchRating("Fitch Rating");
        ratingTest1.setOrderNumber(10);

        Rating ratingTest2 = new Rating();
        ratingTest2.setMoodysRating("Moodys Rating");
        ratingTest2.setSandPRating("Sand PRating");
        ratingTest2.setFitchRating("Fitch Rating");
        ratingTest2.setOrderNumber(20);

        Rating ratingTest3 = new Rating();
        ratingTest3.setMoodysRating("Moodys Rating");
        ratingTest3.setSandPRating("Sand PRating");
        ratingTest3.setFitchRating("Fitch Rating");
        ratingTest3.setOrderNumber(30);

        List<Rating> allRatingsToFind = new ArrayList<>();
        allRatingsToFind.add(ratingTest1);
        allRatingsToFind.add(ratingTest2);
        allRatingsToFind.add(ratingTest3);

        doReturn(allRatingsToFind).when(mockRatingService).findAllRatings();

        doNothing().when(mockRatingService).deleteRatingById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/rating/delete/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/rating/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, times(1)).deleteRatingById(1);
        verify(mockRatingService, times(1)).findAllRatings();
    }

    // @GetMapping(value = "/rating/delete/{id}"")
    @Test
    public void deleteRating_whenRatingNotExist() {
        //ARRANGE
        doThrow(RecordNotFoundException.class).when(mockRatingService).deleteRatingById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/rating/delete/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("errorRecordNotFound"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, times(1)).deleteRatingById(1);
        verify(mockRatingService, never()).findAllRatings();
    }

}