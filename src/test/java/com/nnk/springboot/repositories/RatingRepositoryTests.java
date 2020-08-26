package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;

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
 * Class including tests for the Rating entity Repository.
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class RatingRepositoryTests {

    @Autowired
    private RatingRepository ratingRepositoryUnderTest;

    @Test
    public void saveRating() {
        // ARRANGE
        Rating ratingToSave = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);

        // ACT
        Rating ratingSaved = ratingRepositoryUnderTest.save(ratingToSave);

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
        Rating ratingToUpdate = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingRepositoryUnderTest.save(ratingToUpdate);

        // ACT
        ratingToUpdate.setOrderNumber(20);
        Rating ratingUpdated = ratingRepositoryUnderTest.save(ratingToUpdate);

        // ASSERT
        assertEquals(ratingToUpdate.getOrderNumber(), ratingUpdated.getOrderNumber());
        assertEquals(ratingToUpdate.getFitchRating(), ratingUpdated.getFitchRating());
        assertEquals(ratingToUpdate.getMoodysRating(), ratingUpdated.getMoodysRating());
        assertEquals(ratingToUpdate.getSandPRating(), ratingUpdated.getSandPRating());
    }

    @Test
    public void findRatingById() {
        // ARRANGE
        Rating ratingToFind = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToFind = ratingRepositoryUnderTest.save(ratingToFind);

        // ACT
        Optional<Rating> ratingFound = ratingRepositoryUnderTest.findById(ratingToFind.getId());

        // ASSERT
        assertTrue(ratingFound.isPresent());
        assertEquals(ratingToFind.getOrderNumber(), ratingFound.get().getOrderNumber());
        assertEquals(ratingToFind.getFitchRating(), ratingFound.get().getFitchRating());
        assertEquals(ratingToFind.getMoodysRating(), ratingFound.get().getMoodysRating());
        assertEquals(ratingToFind.getSandPRating(), ratingFound.get().getSandPRating());
    }

    @Test
    public void findAllRatings() {
        // ARRANGE
        Rating ratingToFind1 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingRepositoryUnderTest.save(ratingToFind1);

        Rating ratingToFind2 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingRepositoryUnderTest.save(ratingToFind2);

        Rating ratingToFind3 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingRepositoryUnderTest.save(ratingToFind3);

        // ACT
        List<Rating> listRatings = ratingRepositoryUnderTest.findAll();

        // ASSERT
        assertEquals(3, listRatings.size());
    }

    @Test
    public void deleteRatingById() {
        // ARRANGE
        Rating ratingToDelete = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToDelete = ratingRepositoryUnderTest.save(ratingToDelete);

        // ACT
        Integer id = ratingToDelete.getId();
        ratingRepositoryUnderTest.deleteById(id);

        // ASSERT
        Optional<Rating> ratingDeleted = ratingRepositoryUnderTest.findById(id);
        assertFalse(ratingDeleted.isPresent());
    }
}
