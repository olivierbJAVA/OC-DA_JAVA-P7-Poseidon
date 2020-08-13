package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the database) tests for the
 * Rating Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql({"/schema-test.sql"})
public class RatingServiceITests {

    @Autowired
    private RatingImplService ratingImplServiceUnderTest;

    @Test
    public void ratingTests() {
        //Rating ratingTest = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        Rating ratingTest = new Rating();
        ratingTest.setMoodysRating("Moodys Rating");
        ratingTest.setSandPRating("SandP Rating");
        ratingTest.setFitchRating("Fitch Rating");
        ratingTest.setOrderNumber(10);

        // Save
        ratingTest = ratingImplServiceUnderTest.createRating(ratingTest);
        assertNotNull(ratingTest.getId());
        assertTrue(ratingTest.getOrderNumber() == 10);

        // Update
        ratingTest.setOrderNumber(20);
        ratingTest = ratingImplServiceUnderTest.updateRating(ratingTest);
        assertTrue(ratingTest.getOrderNumber() == 20);

        // Find by id
        Rating ratingGet = ratingImplServiceUnderTest.findRatingById(ratingTest.getId());
        assertNotNull(ratingGet);
        assertEquals(ratingTest.getOrderNumber(), ratingGet.getOrderNumber());
        assertEquals(ratingTest.getFitchRating(), ratingGet.getFitchRating());
        assertEquals(ratingTest.getMoodysRating(), ratingGet.getMoodysRating());
        assertEquals(ratingTest.getSandPRating(), ratingGet.getSandPRating());

        // Find all
        Rating ratingTest2 = new Rating();
        ratingTest2.setMoodysRating("Moodys Rating");
        ratingTest2.setSandPRating("SandP Rating");
        ratingTest2.setFitchRating("Fitch Rating");
        ratingTest2.setOrderNumber(10);
        ratingImplServiceUnderTest.createRating(ratingTest2);

        Rating ratingTest3 = new Rating();
        ratingTest3.setMoodysRating("Moodys Rating");
        ratingTest3.setSandPRating("SandP Rating");
        ratingTest3.setFitchRating("Fitch Rating");
        ratingTest3.setOrderNumber(10);
        ratingImplServiceUnderTest.createRating(ratingTest3);

        List<Rating> listResult = ratingImplServiceUnderTest.findAllRatings();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = ratingTest.getId();
        ratingImplServiceUnderTest.deleteRatingById(id);
        assertThrows(RecordNotFoundException.class, () -> {
            ratingImplServiceUnderTest.findRatingById(id);
        });
    }
}
