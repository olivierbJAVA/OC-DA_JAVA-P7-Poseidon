package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the repository layer) tests for the
 * Rating entity Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql("/schema-test.sql")
public class RatingServiceImplITests {

    @Autowired
    private RatingServiceImpl ratingServiceImplUnderTest;

    @Test
    @Sql("/cleandb-test.sql")
    public void createRating() {
        // ARRANGE
        Rating ratingToCreate = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);

        // ACT
        Rating ratingCreated = ratingServiceImplUnderTest.createRating(ratingToCreate);

        // ASSERT
        assertNotNull(ratingCreated.getId());
        assertEquals(ratingToCreate.getOrderNumber(), ratingCreated.getOrderNumber());
        assertEquals(ratingToCreate.getFitchRating(), ratingCreated.getFitchRating());
        assertEquals(ratingToCreate.getMoodysRating(), ratingCreated.getMoodysRating());
        assertEquals(ratingToCreate.getSandPRating(), ratingCreated.getSandPRating());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void updateRating() {
        // ARRANGE
        Rating ratingToUpdate = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingServiceImplUnderTest.createRating(ratingToUpdate);

        // ACT
        ratingToUpdate.setOrderNumber(20);
        Rating ratingUpdated = ratingServiceImplUnderTest.updateRating(ratingToUpdate);

        // ASSERT
        assertEquals(ratingToUpdate.getOrderNumber(), ratingUpdated.getOrderNumber());
        assertEquals(ratingToUpdate.getFitchRating(), ratingUpdated.getFitchRating());
        assertEquals(ratingToUpdate.getMoodysRating(), ratingUpdated.getMoodysRating());
        assertEquals(ratingToUpdate.getSandPRating(), ratingUpdated.getSandPRating());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findRatingById() {
        // ARRANGE
        Rating ratingToFind = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToFind = ratingServiceImplUnderTest.createRating(ratingToFind);

        // ACT
        Rating ratingFound = ratingServiceImplUnderTest.findRatingById(ratingToFind.getId());

        // ASSERT
        assertNotNull(ratingFound);
        assertEquals(ratingToFind.getOrderNumber(), ratingFound.getOrderNumber());
        assertEquals(ratingToFind.getFitchRating(), ratingFound.getFitchRating());
        assertEquals(ratingToFind.getMoodysRating(), ratingFound.getMoodysRating());
        assertEquals(ratingToFind.getSandPRating(), ratingFound.getSandPRating());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findAllRatings() {
        // ARRANGE
        Rating ratingToFind1 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingServiceImplUnderTest.createRating(ratingToFind1);

        Rating ratingToFind2 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingServiceImplUnderTest.createRating(ratingToFind2);

        Rating ratingToFind3 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingServiceImplUnderTest.createRating(ratingToFind3);

        // ACT
        List<Rating> listRatings = ratingServiceImplUnderTest.findAllRatings();

        // ASSERT
        assertEquals(3, listRatings.size());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void deleteRatingById() {
        // ARRANGE
        Rating ratingToDelete = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToDelete = ratingServiceImplUnderTest.createRating(ratingToDelete);

        // ACT
        Integer id = ratingToDelete.getId();
        ratingServiceImplUnderTest.deleteRatingById(id);

        // ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ratingServiceImplUnderTest.findRatingById(id);
        });
    }
}
