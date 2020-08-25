package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class including unit tests for the BidListServiceImpl Class.
 */
@ExtendWith(MockitoExtension.class)
public class BidListServiceImplTests {

    @InjectMocks
    private BidListServiceImpl bidListServiceImplUnderTest;

    @Mock
    private BidListRepository mockBidListRepository;

    @Test
    public void createBidList() {
        // ARRANGE
        BidList bidListToCreate = new BidList("Account", "Type", 10d);
        bidListToCreate.setBidListId(1);
        bidListToCreate.setAskQuantity(30d);
        bidListToCreate.setBid(123.45d);
        bidListToCreate.setAsk(321.54d);
        bidListToCreate.setBenchmark("Benchmark");
        bidListToCreate.setBidListDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToCreate.setCommentary("Commentary");
        bidListToCreate.setSecurity("Security");
        bidListToCreate.setStatus("StatusTest");
        bidListToCreate.setTrader("Trader");
        bidListToCreate.setBook("Book");
        bidListToCreate.setCreationName("CreationName");
        bidListToCreate.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToCreate.setDealName("DealName");
        bidListToCreate.setDealType("DealType");
        bidListToCreate.setSourceListId("SourceListId");
        bidListToCreate.setSide("Side");
        doReturn(bidListToCreate).when(mockBidListRepository).save(bidListToCreate);

        // ACT
        BidList bidListCreated = bidListServiceImplUnderTest.createBidList(bidListToCreate);

        // ASSERT
        verify(mockBidListRepository, times(1)).save(bidListToCreate);
        assertEquals(bidListToCreate, bidListCreated);
    }

    @Test
    public void updateBidList_whenIdExist() {
        // ARRANGE
        BidList bidListToUpdate = new BidList("Account", "Type", 10d);
        bidListToUpdate.setBidListId(1);
        bidListToUpdate.setAskQuantity(30d);
        bidListToUpdate.setBid(123.45d);
        bidListToUpdate.setAsk(321.54d);
        bidListToUpdate.setBenchmark("Benchmark");
        bidListToUpdate.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToUpdate.setCommentary("Commentary");
        bidListToUpdate.setSecurity("Security");
        bidListToUpdate.setStatus("StatusTest");
        bidListToUpdate.setTrader("Trader");
        bidListToUpdate.setBook("Book");
        bidListToUpdate.setCreationName("CreationName");
        bidListToUpdate.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToUpdate.setRevisionName("RevisionName");
        bidListToUpdate.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToUpdate.setDealName("DealName");
        bidListToUpdate.setDealType("DealType");
        bidListToUpdate.setSourceListId("SourceListId");
        bidListToUpdate.setSide("Side");
        doReturn(Optional.of(bidListToUpdate)).when(mockBidListRepository).findById(bidListToUpdate.getBidListId());
        doReturn(bidListToUpdate).when(mockBidListRepository).save(bidListToUpdate);

        // ACT
        BidList bidListUpdated = bidListServiceImplUnderTest.updateBidList(bidListToUpdate);

        // ASSERT
        verify(mockBidListRepository, times(1)).save(bidListToUpdate);
        assertEquals(bidListToUpdate, bidListUpdated);
    }

    @Test
    public void updateBidList_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockBidListRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            bidListServiceImplUnderTest.findBidListById(1);
        });
        verify(mockBidListRepository, never()).save(any(BidList.class));
    }

    @Test
    public void findBidListById_whenIdExist() {
        // ARRANGE
        BidList bidListToFind = new BidList("Account", "Type", 10d);
        bidListToFind.setBidListId(1);
        bidListToFind.setAskQuantity(30d);
        bidListToFind.setBid(123.45d);
        bidListToFind.setAsk(321.54d);
        bidListToFind.setBenchmark("Benchmark");
        bidListToFind.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind.setCommentary("Commentary");
        bidListToFind.setSecurity("Security");
        bidListToFind.setStatus("StatusTest");
        bidListToFind.setTrader("Trader");
        bidListToFind.setBook("Book");
        bidListToFind.setCreationName("CreationName");
        bidListToFind.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToFind.setRevisionName("RevisionName");
        bidListToFind.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind.setDealName("DealName");
        bidListToFind.setDealType("DealType");
        bidListToFind.setSourceListId("SourceListId");
        bidListToFind.setSide("Side");
        doReturn(Optional.of(bidListToFind)).when(mockBidListRepository).findById(bidListToFind.getBidListId());

        // ACT
        BidList bidListFound = bidListServiceImplUnderTest.findBidListById(bidListToFind.getBidListId());

        // ASSERT
        verify(mockBidListRepository, times(1)).findById(bidListToFind.getBidListId());
        assertEquals(bidListToFind, bidListFound);
    }

    @Test
    public void findBidListById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockBidListRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            bidListServiceImplUnderTest.findBidListById(1);
        });
        verify(mockBidListRepository, times(1)).findById(1);
    }

    @Test
    public void findAllBidLists() {
        // ARRANGE
        BidList bidListToFind1 = new BidList("Account", "Type", 10d);
        bidListToFind1.setBidListId(1);
        bidListToFind1.setAskQuantity(30d);
        bidListToFind1.setBid(123.45d);
        bidListToFind1.setAsk(321.54d);
        bidListToFind1.setBenchmark("Benchmark");
        bidListToFind1.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind1.setCommentary("Commentary");
        bidListToFind1.setSecurity("Security");
        bidListToFind1.setStatus("StatusTest");
        bidListToFind1.setTrader("Trader");
        bidListToFind1.setBook("Book");
        bidListToFind1.setCreationName("CreationName");
        bidListToFind1.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToFind1.setRevisionName("RevisionName");
        bidListToFind1.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind1.setDealName("DealName");
        bidListToFind1.setDealType("DealType");
        bidListToFind1.setSourceListId("SourceListId");
        bidListToFind1.setSide("Side");

        BidList bidListToFind2 = new BidList("Account", "Type", 10d);
        bidListToFind2.setBidListId(2);
        bidListToFind2.setAskQuantity(30d);
        bidListToFind2.setBid(123.45d);
        bidListToFind2.setAsk(321.54d);
        bidListToFind2.setBenchmark("Benchmark");
        bidListToFind2.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind2.setCommentary("Commentary");
        bidListToFind2.setSecurity("Security");
        bidListToFind2.setStatus("StatusTest");
        bidListToFind2.setTrader("Trader");
        bidListToFind2.setBook("Book");
        bidListToFind2.setCreationName("CreationName");
        bidListToFind2.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToFind2.setRevisionName("RevisionName");
        bidListToFind2.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind2.setDealName("DealName");
        bidListToFind2.setDealType("DealType");
        bidListToFind2.setSourceListId("SourceListId");
        bidListToFind2.setSide("Side");

        BidList bidListToFind3 = new BidList("Account", "Type", 10d);
        bidListToFind3.setBidListId(3);
        bidListToFind3.setAskQuantity(30d);
        bidListToFind3.setBid(123.45d);
        bidListToFind3.setAsk(321.54d);
        bidListToFind3.setBenchmark("Benchmark");
        bidListToFind3.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind3.setCommentary("Commentary");
        bidListToFind3.setSecurity("Security");
        bidListToFind3.setStatus("StatusTest");
        bidListToFind3.setTrader("Trader");
        bidListToFind3.setBook("Book");
        bidListToFind3.setCreationName("CreationName");
        bidListToFind3.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToFind3.setRevisionName("RevisionName");
        bidListToFind3.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind3.setDealName("DealName");
        bidListToFind3.setDealType("DealType");
        bidListToFind3.setSourceListId("SourceListId");
        bidListToFind3.setSide("Side");

        List<BidList> listBidListsToFind = new ArrayList<>();
        listBidListsToFind.add(bidListToFind1);
        listBidListsToFind.add(bidListToFind2);
        listBidListsToFind.add(bidListToFind3);

        doReturn(listBidListsToFind).when(mockBidListRepository).findAll();

        // ACT
        List<BidList> listBidListsFound = bidListServiceImplUnderTest.findAllBidLists();

        // ASSERT
        verify(mockBidListRepository, times(1)).findAll();
        assertEquals(listBidListsToFind, listBidListsFound);
    }

    @Test
    public void deleteBidListById_whenIdExist() {
        // ARRANGE
        BidList bidListToDelete = new BidList("Account", "Type", 10d);
        bidListToDelete.setBidListId(1);
        bidListToDelete.setAskQuantity(30d);
        bidListToDelete.setBid(123.45d);
        bidListToDelete.setAsk(321.54d);
        bidListToDelete.setBenchmark("Benchmark");
        bidListToDelete.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToDelete.setCommentary("Commentary");
        bidListToDelete.setSecurity("Security");
        bidListToDelete.setStatus("StatusTest");
        bidListToDelete.setTrader("Trader");
        bidListToDelete.setBook("Book");
        bidListToDelete.setCreationName("CreationName");
        bidListToDelete.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToDelete.setRevisionName("RevisionName");
        bidListToDelete.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToDelete.setDealName("DealName");
        bidListToDelete.setDealType("DealType");
        bidListToDelete.setSourceListId("SourceListId");
        bidListToDelete.setSide("Side");
        doReturn(Optional.of(bidListToDelete)).when(mockBidListRepository).findById(bidListToDelete.getBidListId());

        // ACT
        bidListServiceImplUnderTest.deleteBidListById(bidListToDelete.getBidListId());

        // ASSERT
        verify(mockBidListRepository, times(1)).deleteById(bidListToDelete.getBidListId());
    }

    @Test
    public void deleteBidListById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockBidListRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            bidListServiceImplUnderTest.deleteBidListById(1);
        });
        verify(mockBidListRepository, times(1)).findById(1);
        verify(mockBidListRepository, never()).deleteById(1);
    }
}
