package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Class including unit tests for the UserServiceImpl Class.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

    @InjectMocks
    private UserServiceImpl userImplServiceUnderTest;

    @Mock
    private UserRepository mockUserRepository;

    @Test
    public void createUser() {
        // ARRANGE
        User userToCreate = new User("user", "%Password1", "User", "USER");
        userToCreate.setId(1);
        doReturn(userToCreate).when(mockUserRepository).save(userToCreate);

        // ACT
        User userCreated = userImplServiceUnderTest.createUser(userToCreate);

        // ASSERT
        verify(mockUserRepository, times(1)).save(userToCreate);
        assertEquals(userToCreate, userCreated);
    }

    @Test
    public void updateUser_whenIdExist() {
        // ARRANGE
        User userToUpdate = new User("user", "%Password1", "User", "USER");
        userToUpdate.setId(1);
        doReturn(Optional.of(userToUpdate)).when(mockUserRepository).findById(userToUpdate.getId());
        doReturn(userToUpdate).when(mockUserRepository).save(userToUpdate);

        // ACT
        User userUpdated = userImplServiceUnderTest.updateUser(userToUpdate);

        // ASSERT
        verify(mockUserRepository, times(1)).save(userToUpdate);
        assertEquals(userToUpdate, userUpdated);
    }

    @Test
    public void updateUser_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockUserRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            userImplServiceUnderTest.findUserById(1);
        });
        verify(mockUserRepository, never()).save(any(User.class));
    }

    @Test
    public void findUserById_whenIdExist() {
        // ARRANGE
        User userToFind = new User("user", "%Password1", "User", "USER");
        userToFind.setId(1);
        doReturn(Optional.of(userToFind)).when(mockUserRepository).findById(userToFind.getId());

        // ACT
        User userFound = userImplServiceUnderTest.findUserById(userToFind.getId());

        // ASSERT
        verify(mockUserRepository, times(1)).findById(userToFind.getId());
        assertEquals(userToFind, userFound);
    }

    @Test
    public void findUserById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockUserRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            userImplServiceUnderTest.findUserById(1);
        });
        verify(mockUserRepository, times(1)).findById(1);
    }

    @Test
    public void findUserByUsername_whenUsernameExist() {
        // ARRANGE
        User userToFind = new User("user", "%Password1", "User", "USER");
        userToFind.setId(1);
        doReturn(userToFind).when(mockUserRepository).findUserByUsername(userToFind.getUsername());

        // ACT
        User userFound = userImplServiceUnderTest.findUserByUsername(userToFind.getUsername());

        // ASSERT
        verify(mockUserRepository, times(1)).findUserByUsername(userToFind.getUsername());
        assertEquals(userToFind, userFound);
    }

    @Test
    public void findUserByUsername_whenUsernameNotExist() {
        // ARRANGE
        doReturn(null).when(mockUserRepository).findUserByUsername("UserNameNotExist");

        // ACT
        User userUsernameNotExist = userImplServiceUnderTest.findUserByUsername("UserNameNotExist");

        // ASSERT
        assertNull(userUsernameNotExist);
        verify(mockUserRepository, times(1)).findUserByUsername("UserNameNotExist");
    }

    @Test
    public void findAllUsers() {
        // ARRANGE
        User userToFind1 = new User("user1", "%Password1", "User1", "USER");
        userToFind1.setId(1);
        User userToFind2 = new User("user2", "%Password2", "User2", "USER");
        userToFind2.setId(2);
        User userToFind3 = new User("user3", "%Password3", "User3", "USER");
        userToFind3.setId(3);

        List<User> listUsersToFind = new ArrayList<>();
        listUsersToFind.add(userToFind1);
        listUsersToFind.add(userToFind2);
        listUsersToFind.add(userToFind3);

        doReturn(listUsersToFind).when(mockUserRepository).findAll();

        // ACT
        List<User> listUsersFound = userImplServiceUnderTest.findAllUsers();

        // ASSERT
        verify(mockUserRepository, times(1)).findAll();
        assertEquals(listUsersToFind, listUsersFound);
    }

    @Test
    public void deleteUserById_whenIdExist() {
        // ARRANGE
        User userToDelete = new User("user", "%Password1", "User", "USER");
        userToDelete.setId(1);
        doReturn(Optional.of(userToDelete)).when(mockUserRepository).findById(userToDelete.getId());

        // ACT
        userImplServiceUnderTest.deleteUserById(userToDelete.getId());

        // ASSERT
        verify(mockUserRepository, times(1)).deleteById(userToDelete.getId());
    }

    @Test
    public void deleteUserById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockUserRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            userImplServiceUnderTest.deleteUserById(1);
        });
        verify(mockUserRepository, times(1)).findById(1);
        verify(mockUserRepository, never()).deleteById(1);
    }
}
