package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/data-test.sql"})
public class RatingTests {
    /*
        @TestConfiguration
        static class RatingTestsContextConfiguration {
            @Bean
            public RatingRepository ratingRepository() {
                return new RatingRepository();
            }
        }
    */
    @Autowired
    private RatingRepository ratingRepositoryUnderTest;

    @Test
    public void ratingTests() {
        Rating ratingTest = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        // Save
        ratingTest = ratingRepositoryUnderTest.save(ratingTest);
        assertNotNull(ratingTest.getId());
        assertTrue(ratingTest.getOrderNumber() == 10);

        // Update
        ratingTest.setOrderNumber(20);
        ratingTest = ratingRepositoryUnderTest.save(ratingTest);
        assertTrue(ratingTest.getOrderNumber() == 20);

        // Find by id
        Optional<Rating> ratingGet = ratingRepositoryUnderTest.findById(ratingTest.getId());
        assertTrue(ratingGet.isPresent());
        assertEquals(ratingTest.getOrderNumber(), ratingGet.get().getOrderNumber());
        assertEquals(ratingTest.getFitchRating(), ratingGet.get().getFitchRating());
        assertEquals(ratingTest.getMoodysRating(), ratingGet.get().getMoodysRating());
        assertEquals(ratingTest.getSandPRating(), ratingGet.get().getSandPRating());

        // Find all
        List<Rating> listResult = ratingRepositoryUnderTest.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = ratingTest.getId();
        ratingRepositoryUnderTest.delete(ratingTest);
        Optional<Rating> ratingList = ratingRepositoryUnderTest.findById(id);
        assertFalse(ratingList.isPresent());
    }
}
