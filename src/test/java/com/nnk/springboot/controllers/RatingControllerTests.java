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

        @InjectMocks
        RatingController ratingcontroller;

        @MockBean
        private IRatingService mockRatingService;

        @BeforeEach
        private void setUpPerTest() {
        }

        // @GetMapping(value = "/rating/list")
        @Test
        public void findAllRatings() {
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

            when(mockRatingService.findAllRatings()).thenReturn(allRatingsToFind);

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

    // @GetMapping(value = "/rating/update/{id}"")
    @Test
    public void ratingUpdate() {
        //ARRANGE
        Rating ratingTest1 = new Rating();
        ratingTest1.setId(1);
        ratingTest1.setMoodysRating("Moodys Rating");
        ratingTest1.setSandPRating("Sand PRating");
        ratingTest1.setFitchRating("Fitch Rating");
        ratingTest1.setOrderNumber(10);

        when(mockRatingService.findRatingById(1)).thenReturn(ratingTest1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/rating/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("rating", ratingTest1))
                    .andExpect(view().name("rating/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, times(1)).findRatingById(1);
    }

    // @GetMapping(value = "/rating/delete/{id}"")
    @Test
    public void ratingDelete() {
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

        when(mockRatingService.findAllRatings()).thenReturn(allRatingsToFind);

        doNothing().when(mockRatingService).deleteRatingById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/rating/delete/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location","/rating/list"));
                    //.andExpect(model().attribute("ratings", allRatingsToFind));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRatingService, times(1)).deleteRatingById(1);
        verify(mockRatingService, times(1)).findAllRatings();
    }

/*
        // @GetMapping(value = "/persons/{id}")
        @Test
        public void getPersonById_whenPersonExist() {
            //ARRANGE
            Person personToGet = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToGet.getIdPerson())).thenReturn(personToGet);

            //ACT & ASSERT
            try {
                mockMvc.perform(get("/persons/{id}", personToGet.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isFound())
                        .andExpect(jsonPath("$.firstName", is(personToGet.getFirstName())));
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(1)).getPersonById(personToGet.getIdPerson());
        }

        // @GetMapping(value = "/persons/{id}")
        @Test
        public void getPersonById_whenPersonNotExist() {
            //ARRANGE
            Person personToGet = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToGet.getIdPerson())).thenReturn(null);

            //ACT & ASSERT
            try {
                mockMvc.perform(get("/persons/{id}", personToGet.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(1)).getPersonById(personToGet.getIdPerson());
        }

        // @PostMapping(value = "/persons")
        @Test
        public void addPerson_whenPersonNotAlreadyExist() {
            //ARRANGE
            Person personToAdd = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.personExist(any(Person.class))).thenReturn(false).thenReturn(true);
            when(mockRatingService.addPerson(personToAdd)).thenReturn(null);

            //ACT & ASSERT
            MvcResult mvcResult=null;
            try {
                mvcResult = mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personToAdd)))
                        .andExpect(status().isCreated())
                        .andReturn();
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(2)).personExist(any(Person.class));
            verify(mockRatingService, times(1)).addPerson(personToAdd);

            String actualResponseHeaderLocation = mvcResult.getResponse().getHeader("Location");
            assertEquals("http://localhost/persons/BertrandSimon", actualResponseHeaderLocation);
        }

        // @PostMapping(value = "/persons")
        @Test
        public void addPerson_whenPersonAlreadyExist() {
            //ARRANGE
            Person personToAdd = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.personExist(any(Person.class))).thenReturn(true);

            //ACT & ASSERT
            try {
                mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personToAdd)))
                        .andExpect(status().isBadRequest());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(1)).personExist(any(Person.class));
            verify(mockRatingService, never()).addPerson(personToAdd);
        }

        // @PostMapping(value = "/persons")
        @Test
        public void addPerson_whenPersonNotAlreadyExist_whenInternalServerError() {
            //ARRANGE
            Person personToAdd = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.personExist(any(Person.class))).thenReturn(false).thenReturn(false);
            when(mockRatingService.addPerson(personToAdd)).thenReturn(null);

            //ACT & ASSERT
            try {
                mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personToAdd)))
                        .andExpect(status().isInternalServerError());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(2)).personExist(any(Person.class));
            verify(mockRatingService, times(1)).addPerson(personToAdd);
        }

        // @PutMapping(value = "/persons/{id}")
        @Test
        public void updatePerson_whenPersonExist() {
            //ARRANGE
            Person personToUpdate = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");
            Person personUpdated = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Courcelles", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToUpdate.getIdPerson())).thenReturn(personToUpdate).thenReturn(personToUpdate).thenReturn(personUpdated);
            when(mockRatingService.updatePerson(personUpdated)).thenReturn(personToUpdate);

            //ACT & ASSERT
            try {
                mockMvc.perform(put("/persons/{id}", personToUpdate.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personUpdated)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.address", is(personUpdated.getAddress())));
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(3)).getPersonById(personToUpdate.getIdPerson());
            verify(mockRatingService, times(1)).updatePerson(personUpdated);
        }

        // @PutMapping(value = "/persons/{id}")
        @Test
        public void updatePerson_whenPersonInPathRequestNotExist() {
            //ARRANGE
            Person personToUpdate = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");
            Person personUpdated = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Courcelles", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToUpdate.getIdPerson())).thenReturn(null);

            //ACT & ASSERT
            try {
                mockMvc.perform(put("/persons/{id}", personToUpdate.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personUpdated)))
                        .andExpect(status().isNotFound());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(1)).getPersonById(personToUpdate.getIdPerson());
            verify(mockRatingService, never()).updatePerson(any(Person.class));
        }

        // @PutMapping(value = "/persons/{id}")
        @Test
        public void updatePerson_whenPersonInRequestBodyNotExist() {
            //ARRANGE
            Person personToUpdate = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");
            Person personUpdated = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Courcelles", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToUpdate.getIdPerson())).thenReturn(personToUpdate).thenReturn(null);

            //ACT & ASSERT
            try {
                mockMvc.perform(put("/persons/{id}", personToUpdate.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personUpdated)))
                        .andExpect(status().isNotFound());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(2)).getPersonById(personToUpdate.getIdPerson());
            verify(mockRatingService, never()).updatePerson(any(Person.class));
        }

        // @PutMapping(value = "/persons/{id}")
        @Test
        public void updatePerson_whenPersonExist_whenInternalServerError() {
            //ARRANGE
            Person personToUpdate = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");
            Person personUpdated = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Courcelles", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToUpdate.getIdPerson())).thenReturn(personToUpdate).thenReturn(personToUpdate).thenReturn(personToUpdate);
            when(mockRatingService.updatePerson(personUpdated)).thenReturn(personToUpdate);

            //ACT & ASSERT
            try {
                mockMvc.perform(put("/persons/{id}", personToUpdate.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personUpdated)))
                        .andExpect(status().isInternalServerError());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(3)).getPersonById(personToUpdate.getIdPerson());
            verify(mockRatingService, times(1)).updatePerson(personUpdated);
        }

        // @DeleteMapping(value = "/persons/{id}")
        @Test
        public void deletePerson_whenPersonExist() {
            //ARRANGE
            Person personToDelete = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToDelete.getIdPerson())).thenReturn(personToDelete);
            when(mockRatingService.deletePerson(personToDelete.getIdPerson())).thenReturn(personToDelete);
            when(mockRatingService.personExist(personToDelete)).thenReturn(false);

            //ACT & ASSERT
            try {
                mockMvc.perform(delete("/persons/{id}", personToDelete.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isGone());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(1)).getPersonById(personToDelete.getIdPerson());
            verify(mockRatingService, times(1)).deletePerson(personToDelete.getIdPerson());
            verify(mockRatingService, times(1)).personExist(personToDelete);
        }

        // @DeleteMapping(value = "/persons/{id}")
        @Test
        public void deletePerson_whenPersonNotExist() {
            //ARRANGE
            Person personToDelete = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToDelete.getIdPerson())).thenReturn(null);

            //ACT & ASSERT
            try {
                mockMvc.perform(delete("/persons/{id}", personToDelete.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(1)).getPersonById(personToDelete.getIdPerson());
            verify(mockRatingService, never()).deletePerson(anyString());
            verify(mockRatingService, never()).personExist(any(Person.class));
        }

        // @DeleteMapping(value = "/persons/{id}")
        @Test
        public void deletePerson_whenPersonExist_whenInternalServerError() {
            //ARRANGE
            Person personToDelete = new Person("BertrandSimon", "Bertrand", "Simon", "2 rue de Paris", "Paris", "75000",
                    "0696469887", "bs@email.com");

            when(mockRatingService.getPersonById(personToDelete.getIdPerson())).thenReturn(personToDelete);
            when(mockRatingService.deletePerson(personToDelete.getIdPerson())).thenReturn(personToDelete);
            when(mockRatingService.personExist(personToDelete)).thenReturn(true);

            //ACT & ASSERT
            try {
                mockMvc.perform(delete("/persons/{id}", personToDelete.getIdPerson())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isInternalServerError());
            } catch (Exception e) {
                logger.error("Error in MockMvc", e);
            }

            verify(mockRatingService, times(1)).getPersonById(personToDelete.getIdPerson());
            verify(mockRatingService, times(1)).deletePerson(personToDelete.getIdPerson());
            verify(mockRatingService, times(1)).personExist(personToDelete);
        }
  */
    }