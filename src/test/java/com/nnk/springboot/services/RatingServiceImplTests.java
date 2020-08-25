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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class including unit tests for the RatingServiceImpl Class.
 */
@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTests {

    @InjectMocks
    private RatingServiceImpl ratingImplServiceUnderTest;

    @Mock
    private RatingRepository mockRatingRepository;

    @Test
    public void createRating() {
        // ARRANGE
        Rating ratingToCreate = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToCreate.setId(1);
        doReturn(ratingToCreate).when(mockRatingRepository).save(ratingToCreate);

        // ACT
        Rating ratingCreated = ratingImplServiceUnderTest.createRating(ratingToCreate);

        // ASSERT
        verify(mockRatingRepository, times(1)).save(ratingToCreate);
        assertEquals(ratingToCreate, ratingCreated);
    }

    @Test
    public void updateRating_whenIdExist() {
        // ARRANGE
        Rating ratingToUpdate = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToUpdate.setId(1);
        doReturn(Optional.of(ratingToUpdate)).when(mockRatingRepository).findById(ratingToUpdate.getId());
        doReturn(ratingToUpdate).when(mockRatingRepository).save(ratingToUpdate);

        // ACT
        Rating ratingUpdated = ratingImplServiceUnderTest.updateRating(ratingToUpdate);

        // ASSERT
        verify(mockRatingRepository, times(1)).save(ratingToUpdate);
        assertEquals(ratingToUpdate, ratingUpdated);
    }

    @Test
    public void updateRating_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockRatingRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ratingImplServiceUnderTest.findRatingById(1);
        });
        verify(mockRatingRepository, never()).save(any(Rating.class));
    }

    @Test
    public void findRatingById_whenIdExist() {
        // ARRANGE
        Rating ratingToFind = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToFind.setId(1);
        doReturn(Optional.of(ratingToFind)).when(mockRatingRepository).findById(ratingToFind.getId());

        // ACT
        Rating ratingFound = ratingImplServiceUnderTest.findRatingById(ratingToFind.getId());

        // ASSERT
        verify(mockRatingRepository, times(1)).findById(ratingToFind.getId());
        assertEquals(ratingToFind, ratingFound);
    }

    @Test
    public void findRatingById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockRatingRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ratingImplServiceUnderTest.findRatingById(1);
        });
        verify(mockRatingRepository, times(1)).findById(1);
    }

    @Test
    public void findAllRatings() {
        // ARRANGE
        Rating ratingToFind1 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToFind1.setId(1);
        Rating ratingToFind2 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToFind2.setId(2);
        Rating ratingToFind3 = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
        ratingToFind3.setId(3);

        List<Rating> listRatingsToFind = new ArrayList<>();
        listRatingsToFind.add(ratingToFind1);
        listRatingsToFind.add(ratingToFind2);
        listRatingsToFind.add(ratingToFind3);

        doReturn(listRatingsToFind).when(mockRatingRepository).findAll();

        // ACT
        List<Rating> listRatingsFound = ratingImplServiceUnderTest.findAllRatings();

        // ASSERT
        verify(mockRatingRepository, times(1)).findAll();
        assertEquals(listRatingsToFind, listRatingsFound);
    }

    @Test
    public void deleteRatingById_whenIdExist() {
        // ARRANGE
        Rating ratingToDelete = new Rating("Moodys Rating", "SandP Rating", "Fitch Rating", 10);
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
        doReturn(Optional.empty()).when(mockRatingRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ratingImplServiceUnderTest.deleteRatingById(1);
        });
        verify(mockRatingRepository, times(1)).findById(1);
        verify(mockRatingRepository, never()).deleteById(1);
    }
}
