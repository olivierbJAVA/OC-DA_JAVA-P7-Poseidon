package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
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
        BidList bidListToCreate = new BidList("Account Test", "Type Test", 10d);
        bidListToCreate.setAskQuantity(30d);
        bidListToCreate.setBid(123.45d);
        bidListToCreate.setAsk(321.54d);
        bidListToCreate.setBenchmark("Benchmark Test");
        bidListToCreate.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToCreate.setCommentary("Commentary Test");
        bidListToCreate.setSecurity("Security Test");
        bidListToCreate.setStatus("StatusTest");
        bidListToCreate.setTrader("Trader Test");
        bidListToCreate.setBook("Book Test");
        bidListToCreate.setCreationName("CreationName Test");
        bidListToCreate.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToCreate.setRevisionName("RevisionName Test");
        bidListToCreate.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListToCreate.setDealName("DealName Test");
        bidListToCreate.setDealType("DealType Test");
        bidListToCreate.setSourceListId("SourceListId Test");
        bidListToCreate.setSide("Side Test");
        doReturn(bidListToCreate).when(mockBidListRepository).save(bidListToCreate);

        // ACT
        bidListServiceImplUnderTest.createBidList(bidListToCreate);

        // ASSERT
        verify(mockBidListRepository, times(1)).save(bidListToCreate);
    }

    @Test
    public void updateBidList() {
        // ARRANGE
        BidList bidListToUpdate = new BidList("Account Test", "Type Test", 10d);
        bidListToUpdate.setAskQuantity(30d);
        bidListToUpdate.setBid(123.45d);
        bidListToUpdate.setAsk(321.54d);
        bidListToUpdate.setBenchmark("Benchmark Test");
        bidListToUpdate.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToUpdate.setCommentary("Commentary Test");
        bidListToUpdate.setSecurity("Security Test");
        bidListToUpdate.setStatus("StatusTest");
        bidListToUpdate.setTrader("Trader Test");
        bidListToUpdate.setBook("Book Test");
        bidListToUpdate.setCreationName("CreationName Test");
        bidListToUpdate.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToUpdate.setRevisionName("RevisionName Test");
        bidListToUpdate.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListToUpdate.setDealName("DealName Test");
        bidListToUpdate.setDealType("DealType Test");
        bidListToUpdate.setSourceListId("SourceListId Test");
        bidListToUpdate.setSide("Side Test");
        doReturn(bidListToUpdate).when(mockBidListRepository).save(bidListToUpdate);

        // ACT
        bidListServiceImplUnderTest.updateBidList(bidListToUpdate);

        // ASSERT
        verify(mockBidListRepository, times(1)).save(bidListToUpdate);
    }

    @Test
    public void findBidListById_whenIdExist() {
        // ARRANGE
        BidList bidListToFind = new BidList("Account Test", "Type Test", 10d);
        bidListToFind.setAskQuantity(30d);
        bidListToFind.setBid(123.45d);
        bidListToFind.setAsk(321.54d);
        bidListToFind.setBenchmark("Benchmark Test");
        bidListToFind.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind.setCommentary("Commentary Test");
        bidListToFind.setSecurity("Security Test");
        bidListToFind.setStatus("StatusTest");
        bidListToFind.setTrader("Trader Test");
        bidListToFind.setBook("Book Test");
        bidListToFind.setCreationName("CreationName Test");
        bidListToFind.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToFind.setRevisionName("RevisionName Test");
        bidListToFind.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListToFind.setDealName("DealName Test");
        bidListToFind.setDealType("DealType Test");
        bidListToFind.setSourceListId("SourceListId Test");
        bidListToFind.setSide("Side Test");
        bidListToFind.setBidListId(1);
        doReturn(Optional.of(bidListToFind)).when(mockBidListRepository).findById(bidListToFind.getBidListId());

        // ACT
        bidListServiceImplUnderTest.findBidListById(bidListToFind.getBidListId());

        // ASSERT
        verify(mockBidListRepository, times(1)).findById(bidListToFind.getBidListId());
    }

    @Test
    public void findBidListById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockBidListRepository).findById(anyInt());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            bidListServiceImplUnderTest.findBidListById(1);
        });
        verify(mockBidListRepository, times(1)).findById(1);
    }

    @Test
    public void findAllRatings() {
        // ARRANGE
        BidList bidListToFind1 = new BidList("Account Test", "Type Test", 10d);
        bidListToFind1.setAskQuantity(30d);
        bidListToFind1.setBid(123.45d);
        bidListToFind1.setAsk(321.54d);
        bidListToFind1.setBenchmark("Benchmark Test");
        bidListToFind1.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind1.setCommentary("Commentary Test");
        bidListToFind1.setSecurity("Security Test");
        bidListToFind1.setStatus("StatusTest");
        bidListToFind1.setTrader("Trader Test");
        bidListToFind1.setBook("Book Test");
        bidListToFind1.setCreationName("CreationName Test");
        bidListToFind1.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToFind1.setRevisionName("RevisionName Test");
        bidListToFind1.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListToFind1.setDealName("DealName Test");
        bidListToFind1.setDealType("DealType Test");
        bidListToFind1.setSourceListId("SourceListId Test");
        bidListToFind1.setSide("Side Test");

        BidList bidListToFind2 = new BidList("Account Test", "Type Test", 10d);
        bidListToFind2.setAskQuantity(30d);
        bidListToFind2.setBid(123.45d);
        bidListToFind2.setAsk(321.54d);
        bidListToFind2.setBenchmark("Benchmark Test");
        bidListToFind2.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind2.setCommentary("Commentary Test");
        bidListToFind2.setSecurity("Security Test");
        bidListToFind2.setStatus("StatusTest");
        bidListToFind2.setTrader("Trader Test");
        bidListToFind2.setBook("Book Test");
        bidListToFind2.setCreationName("CreationName Test");
        bidListToFind2.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToFind2.setRevisionName("RevisionName Test");
        bidListToFind2.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListToFind2.setDealName("DealName Test");
        bidListToFind2.setDealType("DealType Test");
        bidListToFind2.setSourceListId("SourceListId Test");
        bidListToFind2.setSide("Side Test");

        BidList bidListToFind3 = new BidList("Account Test", "Type Test", 10d);
        bidListToFind3.setAskQuantity(30d);
        bidListToFind3.setBid(123.45d);
        bidListToFind3.setAsk(321.54d);
        bidListToFind3.setBenchmark("Benchmark Test");
        bidListToFind3.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToFind3.setCommentary("Commentary Test");
        bidListToFind3.setSecurity("Security Test");
        bidListToFind3.setStatus("StatusTest");
        bidListToFind3.setTrader("Trader Test");
        bidListToFind3.setBook("Book Test");
        bidListToFind3.setCreationName("CreationName Test");
        bidListToFind3.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToFind3.setRevisionName("RevisionName Test");
        bidListToFind3.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListToFind3.setDealName("DealName Test");
        bidListToFind3.setDealType("DealType Test");
        bidListToFind3.setSourceListId("SourceListId Test");
        bidListToFind3.setSide("Side Test");
        List<Rating> listRatings = new ArrayList<>();

        List<BidList> listBidLists = new ArrayList<>();
        listBidLists.add(bidListToFind1);
        listBidLists.add(bidListToFind2);
        listBidLists.add(bidListToFind3);

        doReturn(listBidLists).when(mockBidListRepository).findAll();

        // ACT
        bidListServiceImplUnderTest.findAllBidLists();

        // ASSERT
        verify(mockBidListRepository, times(1)).findAll();
    }

    @Test
    public void deleteBidListById_whenIdExist() {
        // ARRANGE
        BidList bidListToDelete = new BidList("Account Test", "Type Test", 10d);
        bidListToDelete.setAskQuantity(30d);
        bidListToDelete.setBid(123.45d);
        bidListToDelete.setAsk(321.54d);
        bidListToDelete.setBenchmark("Benchmark Test");
        bidListToDelete.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToDelete.setCommentary("Commentary Test");
        bidListToDelete.setSecurity("Security Test");
        bidListToDelete.setStatus("StatusTest");
        bidListToDelete.setTrader("Trader Test");
        bidListToDelete.setBook("Book Test");
        bidListToDelete.setCreationName("CreationName Test");
        bidListToDelete.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToDelete.setRevisionName("RevisionName Test");
        bidListToDelete.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListToDelete.setDealName("DealName Test");
        bidListToDelete.setDealType("DealType Test");
        bidListToDelete.setSourceListId("SourceListId Test");
        bidListToDelete.setSide("Side Test");
        bidListToDelete.setBidListId(1);
        doReturn(Optional.of(bidListToDelete)).when(mockBidListRepository).findById(bidListToDelete.getBidListId());

        // ACT
        bidListServiceImplUnderTest.deleteBidListById(bidListToDelete.getBidListId());

        // ASSERT
        verify(mockBidListRepository, times(1)).deleteById(bidListToDelete.getBidListId());
    }

    @Test
    public void deleteBidListById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockBidListRepository).findById(anyInt());

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            bidListServiceImplUnderTest.deleteBidListById(1);
        });
        verify(mockBidListRepository, times(1)).findById(1);
        verify(mockBidListRepository, never()).deleteById(1);
    }
}
