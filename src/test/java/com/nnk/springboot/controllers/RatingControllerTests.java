package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.services.IRatingService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class including unit tests for the RatingController Class.
 */
//@WebMvcTest(value = RatingController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
//@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
@WebMvcTest(value = RatingController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class RatingControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(RatingControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRatingService mockRatingService;

    // @RequestMapping(value = "/rating/list")
    @Test
    public void home() {
        //ARRANGE
        Rating ratingTest1 = new Rating();
        ratingTest1.setId(1);
        ratingTest1.setMoodysRating("Moodys Rating");
        ratingTest1.setSandPRating("Sand PRating");
        ratingTest1.setFitchRating("Fitch Rating");
        ratingTest1.setOrderNumber(10);

        Rating ratingTest2 = new Rating();
        ratingTest2.setId(2);
        ratingTest2.setMoodysRating("Moodys Rating");
        ratingTest2.setSandPRating("Sand PRating");
        ratingTest2.setFitchRating("Fitch Rating");
        ratingTest2.setOrderNumber(20);

        Rating ratingTest3 = new Rating();
        ratingTest3.setId(3);
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

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/rating/update/1")
                    .param("id","1")
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
    }

    // @PostMapping(value = "/rating/update/{id}"")
    @Test
    public void updateRating_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/rating/update/1")
                    .param("id","1")
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
    }

    // @GetMapping(value = "/rating/delete/{id}"")
    @Test
    public void deleteRating_whenRatingExist() {
        //ARRANGE
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
    }

}