package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the rapository layer) tests for the
 * Rating Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql({"/schema-test.sql"})
public class RatingServiceITests {

    @Autowired
    private RatingImplService ratingImplServiceUnderTest;

    @Test
    public void createRating() {
        // ARRANGE
        Rating ratingToSave = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        // ACT
        Rating ratingSaved = ratingImplServiceUnderTest.createRating(ratingToSave);

        // ASSERT
        assertNotNull(ratingSaved.getId());
        assertEquals(ratingToSave.getOrderNumber(), ratingSaved.getOrderNumber());
        assertEquals(ratingToSave.getFitchRating(), ratingSaved.getFitchRating());
        assertEquals(ratingToSave.getMoodysRating(), ratingSaved.getMoodysRating());
        assertEquals(ratingToSave.getSandPRating(), ratingSaved.getSandPRating());
    }

    @Test
    public void updateRating() {
        // ARRANGE
        Rating ratingToUpdate = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingImplServiceUnderTest.createRating(ratingToUpdate);

        // ACT
        ratingToUpdate.setOrderNumber(20);
        Rating ratingUpdated = ratingImplServiceUnderTest.updateRating(ratingToUpdate);

        // ASSERT
        assertEquals(ratingToUpdate.getOrderNumber(), ratingUpdated.getOrderNumber());
        assertEquals(ratingToUpdate.getFitchRating(), ratingUpdated.getFitchRating());
        assertEquals(ratingToUpdate.getMoodysRating(), ratingUpdated.getMoodysRating());
        assertEquals(ratingToUpdate.getSandPRating(), ratingUpdated.getSandPRating());
    }

    @Test
    public void findRatingById() {
        // ARRANGE
        Rating ratingToFind = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingToFind = ratingImplServiceUnderTest.createRating(ratingToFind);

        // ACT
        Rating ratingFound = ratingImplServiceUnderTest.findRatingById(ratingToFind.getId());

        // ASSERT
        assertNotNull(ratingFound);
        assertEquals(ratingToFind.getOrderNumber(), ratingFound.getOrderNumber());
        assertEquals(ratingToFind.getFitchRating(), ratingFound.getFitchRating());
        assertEquals(ratingToFind.getMoodysRating(), ratingFound.getMoodysRating());
        assertEquals(ratingToFind.getSandPRating(), ratingFound.getSandPRating());
    }

    @Test
    public void findAllRatings() {
        // ARRANGE
        Rating ratingToFind1 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingImplServiceUnderTest.createRating(ratingToFind1);

        Rating ratingToFind2 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingImplServiceUnderTest.createRating(ratingToFind2);

        Rating ratingToFind3 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingImplServiceUnderTest.createRating(ratingToFind3);

        // ACT
        List<Rating> listRatings = ratingImplServiceUnderTest.findAllRatings();

        // ASSERT
        assertTrue(listRatings.size() == 3);
    }

    @Test
    public void deleteRatingById() {
        // ARRANGE
        Rating ratingToDelete = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingToDelete = ratingImplServiceUnderTest.createRating(ratingToDelete);

        // ACT
        Integer id = ratingToDelete.getId();
        ratingImplServiceUnderTest.deleteRatingById(id);

        // ASSERT
        assertThrows(RecordNotFoundException.class, () -> {
            ratingImplServiceUnderTest.findRatingById(id);
        });
    }
}
