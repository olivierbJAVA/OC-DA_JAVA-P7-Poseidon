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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class including unit tests for the USerServiceImpl Class.
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
        User userToCreate = new User("Username", "Password", "Fullname", "Role"  );
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
        User userToUpdate = new User("Username", "Password", "Fullname", "Role"  );
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
        User userToFind = new User("Username", "Password", "Fullname", "Role"  );
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
    public void findAllUsers() {
        // ARRANGE
        User userToFind1 = new User("Username1", "Password1", "Fullname1", "Role1"  );
        userToFind1.setId(1);
        User userToFind2 = new User("Username2", "Password2", "Fullname2", "Role2"  );
        userToFind2.setId(2);
        User userToFind3 = new User("Username3", "Password3", "Fullname3", "Role3"  );
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
        User userToDelete = new User("Username", "Password", "Fullname", "Role"  );
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
