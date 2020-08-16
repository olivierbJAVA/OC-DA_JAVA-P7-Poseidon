package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class including unit tests for the RatingServiceImpl Class.
 */
@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTests {
    /*
        @TestConfiguration
        static class RatingServiceImplTestContextConfiguration {
            @Bean
            public IRatingService iRatingService() {
                return new RatingServiceImpl();
            }
        }
    */
    @InjectMocks
    private RatingServiceImpl ratingImplServiceUnderTest;

    @Mock
    private RatingRepository mockRatingRepository;

    @Test
    public void createRating() {
        // ARRANGE
        Rating ratingToCreate = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        doReturn(ratingToCreate).when(mockRatingRepository).save(ratingToCreate);

        // ACT
        ratingImplServiceUnderTest.createRating(ratingToCreate);

        // ASSERT
        verify(mockRatingRepository, times(1)).save(ratingToCreate);
    }

    @Test
    public void updateRating() {
        // ARRANGE
        Rating ratingToUpdate = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        doReturn(ratingToUpdate).when(mockRatingRepository).save(ratingToUpdate);

        // ACT
        ratingImplServiceUnderTest.updateRating(ratingToUpdate);

        // ASSERT
        verify(mockRatingRepository, times(1)).save(ratingToUpdate);
    }

    @Test
    public void findRatingById_whenIdExist() {
        // ARRANGE
        Rating ratingToFind = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingToFind.setId(1);
        doReturn(Optional.of(ratingToFind)).when(mockRatingRepository).findById(ratingToFind.getId());

        // ACT
        ratingImplServiceUnderTest.findRatingById(ratingToFind.getId());

        // ASSERT
        verify(mockRatingRepository, times(1)).findById(ratingToFind.getId());
    }

    @Test
    public void findRatingById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockRatingRepository).findById(anyInt());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ratingImplServiceUnderTest.findRatingById(1);
        });
        verify(mockRatingRepository, times(1)).findById(1);
    }

    @Test
    public void findAllRatings() {
        // ARRANGE
        Rating ratingToFind1 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        Rating ratingToFind2 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        Rating ratingToFind3 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        List<Rating> listRatings = new ArrayList<>();
        listRatings.add(ratingToFind1);
        listRatings.add(ratingToFind2);
        listRatings.add(ratingToFind3);

        doReturn(listRatings).when(mockRatingRepository).findAll();

        // ACT
        ratingImplServiceUnderTest.findAllRatings();

        // ASSERT
        verify(mockRatingRepository, times(1)).findAll();
    }

    @Test
    public void deleteRatingById_whenIdExist() {
        // ARRANGE
        Rating ratingToDelete = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingToDelete.setId(1);
        doReturn(Optional.of(ratingToDelete)).when(mockRatingRepository).findById(ratingToDelete.getId());

        // ACT
        ratingImplServiceUnderTest.deleteRatingById(ratingToDelete.getId());

        // ASSERT
        verify(mockRatingRepository, times(1)).deleteById(ratingToDelete.getId());
    }

    @Test
    public void deleteRatingById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockRatingRepository).findById(anyInt());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ratingImplServiceUnderTest.deleteRatingById(1);
        });
        verify(mockRatingRepository, times(1)).findById(1);
        verify(mockRatingRepository, never()).deleteById(1);
    }
}
