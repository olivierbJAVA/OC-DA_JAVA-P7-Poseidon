package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
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
 * Class including unit tests for the RuleNameServiceImpl Class.
 */
@ExtendWith(MockitoExtension.class)
public class RuleNameServiceImplTests {

    @InjectMocks
    private RuleNameServiceImpl ruleNameServiceImplUnderTest;

    @Mock
    private RuleNameRepository mockRuleNameRepository;

    @Test
    public void createRuleName() {
        // ARRANGE
        RuleName ruleNameToCreate = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        doReturn(ruleNameToCreate).when(mockRuleNameRepository).save(ruleNameToCreate);

        // ACT
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToCreate);

        // ASSERT
        verify(mockRuleNameRepository, times(1)).save(ruleNameToCreate);
    }

    @Test
    public void updateRuleName() {
        // ARRANGE
        RuleName ruleNameToUpdate = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        doReturn(Optional.of(ruleNameToUpdate)).when(mockRuleNameRepository).findById(ruleNameToUpdate.getId());
        doReturn(ruleNameToUpdate).when(mockRuleNameRepository).save(ruleNameToUpdate);

        // ACT
        ruleNameServiceImplUnderTest.updateRuleName(ruleNameToUpdate);

        // ASSERT
        verify(mockRuleNameRepository, times(1)).save(ruleNameToUpdate);
    }

    @Test
    public void findRuleNameById_whenIdExist() {
        // ARRANGE
        RuleName ruleNameToFind = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameToFind.setId(1);
        doReturn(Optional.of(ruleNameToFind)).when(mockRuleNameRepository).findById(ruleNameToFind.getId());

        // ACT
        ruleNameServiceImplUnderTest.findRuleNameById(ruleNameToFind.getId());

        // ASSERT
        verify(mockRuleNameRepository, times(1)).findById(ruleNameToFind.getId());
    }

    @Test
    public void findRuleNameById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockRuleNameRepository).findById(anyInt());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ruleNameServiceImplUnderTest.findRuleNameById(1);
        });
        verify(mockRuleNameRepository, times(1)).findById(1);
    }

    @Test
    public void findAllRuleNames() {
        // ARRANGE
        RuleName ruleNameToFind1 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        RuleName ruleNameToFind2 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        RuleName ruleNameToFind3 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        List<RuleName> listRuleNames = new ArrayList<>();
        listRuleNames.add(ruleNameToFind1);
        listRuleNames.add(ruleNameToFind2);
        listRuleNames.add(ruleNameToFind3);

        doReturn(listRuleNames).when(mockRuleNameRepository).findAll();

        // ACT
        ruleNameServiceImplUnderTest.findAllRuleNames();

        // ASSERT
        verify(mockRuleNameRepository, times(1)).findAll();
    }

    @Test
    public void deleteRuleNameById_whenIdExist() {
        // ARRANGE
        RuleName ruleNameToDelete = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameToDelete.setId(1);
        doReturn(Optional.of(ruleNameToDelete)).when(mockRuleNameRepository).findById(ruleNameToDelete.getId());

        // ACT
        ruleNameServiceImplUnderTest.deleteRuleNameById(ruleNameToDelete.getId());

        // ASSERT
        verify(mockRuleNameRepository, times(1)).deleteById(ruleNameToDelete.getId());
    }

    @Test
    public void deleteRuleNameById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockRuleNameRepository).findById(anyInt());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ruleNameServiceImplUnderTest.deleteRuleNameById(1);
        });
        verify(mockRuleNameRepository, times(1)).findById(1);
        verify(mockRuleNameRepository, never()).deleteById(1);
    }
}
