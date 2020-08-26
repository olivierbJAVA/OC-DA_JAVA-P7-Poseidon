package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the repository layer) tests for the
 * BidList entity Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql("/schema-test.sql")
public class BidListServiceImplITests {

    @Autowired
    private BidListServiceImpl bidListServiceImplUnderTest;

    @Test
    @Sql("/cleandb-test.sql")
    public void createBidList() {
        // ARRANGE
        BidList bidListToCreate = new BidList("Account", "Type", 10d);
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

        // ACT
        BidList bidListCreated = bidListServiceImplUnderTest.createBidList(bidListToCreate);

        // ASSERT
        assertNotNull(bidListCreated.getBidListId());
        assertEquals(bidListToCreate.getAccount(), bidListCreated.getAccount());
        assertEquals(bidListToCreate.getType(), bidListCreated.getType());
        assertEquals(bidListToCreate.getBidQuantity(), bidListCreated.getBidQuantity());
        assertEquals(bidListToCreate.getAskQuantity(), bidListCreated.getAskQuantity());
        assertEquals(bidListToCreate.getBid(), bidListCreated.getBid());
        assertEquals(bidListToCreate.getAsk(), bidListCreated.getAsk());
        assertEquals(bidListToCreate.getBenchmark(), bidListCreated.getBenchmark());
        assertEquals(bidListToCreate.getBidListDate(), bidListCreated.getBidListDate());
        assertEquals(bidListToCreate.getCommentary(), bidListCreated.getCommentary());
        assertEquals(bidListToCreate.getSecurity(), bidListCreated.getSecurity());
        assertEquals(bidListToCreate.getStatus(), bidListCreated.getStatus());
        assertEquals(bidListToCreate.getTrader(), bidListCreated.getTrader());
        assertEquals(bidListToCreate.getBook(), bidListCreated.getBook());
        assertEquals(bidListToCreate.getCreationName(), bidListCreated.getCreationName());
        assertEquals(bidListToCreate.getCreationDate(), bidListCreated.getCreationDate());
        assertEquals(bidListToCreate.getRevisionName(), bidListCreated.getRevisionName());
        assertEquals(bidListToCreate.getRevisionDate(), bidListCreated.getRevisionDate());
        assertEquals(bidListToCreate.getDealName(), bidListCreated.getDealName());
        assertEquals(bidListToCreate.getDealType(), bidListCreated.getDealType());
        assertEquals(bidListToCreate.getSourceListId(), bidListCreated.getSourceListId());
        assertEquals(bidListToCreate.getSide(), bidListCreated.getSide());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void updateBidList() {
        // ARRANGE
        BidList bidListToUpdate = new BidList("Account", "Type", 10d);
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
        bidListServiceImplUnderTest.createBidList(bidListToUpdate);

        // ACT
        bidListToUpdate.setBidQuantity(20d);
        BidList bidListUpdated = bidListServiceImplUnderTest.updateBidList(bidListToUpdate);

        // ASSERT
        assertEquals(bidListToUpdate.getAccount(), bidListUpdated.getAccount());
        assertEquals(bidListToUpdate.getType(), bidListUpdated.getType());
        assertEquals(bidListToUpdate.getBidQuantity(), bidListUpdated.getBidQuantity());
        assertEquals(bidListToUpdate.getAskQuantity(), bidListUpdated.getAskQuantity());
        assertEquals(bidListToUpdate.getBid(), bidListUpdated.getBid());
        assertEquals(bidListToUpdate.getAsk(), bidListUpdated.getAsk());
        assertEquals(bidListToUpdate.getBenchmark(), bidListUpdated.getBenchmark());
        assertEquals(bidListToUpdate.getBidListDate(), bidListUpdated.getBidListDate());
        assertEquals(bidListToUpdate.getCommentary(), bidListUpdated.getCommentary());
        assertEquals(bidListToUpdate.getSecurity(), bidListUpdated.getSecurity());
        assertEquals(bidListToUpdate.getStatus(), bidListUpdated.getStatus());
        assertEquals(bidListToUpdate.getTrader(), bidListUpdated.getTrader());
        assertEquals(bidListToUpdate.getBook(), bidListUpdated.getBook());
        assertEquals(bidListToUpdate.getCreationName(), bidListUpdated.getCreationName());
        assertEquals(bidListToUpdate.getCreationDate(), bidListUpdated.getCreationDate());
        assertEquals(bidListToUpdate.getRevisionName(), bidListUpdated.getRevisionName());
        assertEquals(bidListToUpdate.getRevisionDate(), bidListUpdated.getRevisionDate());
        assertEquals(bidListToUpdate.getDealName(), bidListUpdated.getDealName());
        assertEquals(bidListToUpdate.getDealType(), bidListUpdated.getDealType());
        assertEquals(bidListToUpdate.getSourceListId(), bidListUpdated.getSourceListId());
        assertEquals(bidListToUpdate.getSide(), bidListUpdated.getSide());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findBidListById() {
        // ARRANGE
        BidList bidListToFind = new BidList("Account", "Type", 10d);
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
        bidListToFind = bidListServiceImplUnderTest.createBidList(bidListToFind);

        // ACT
        BidList bidListFound = bidListServiceImplUnderTest.findBidListById(bidListToFind.getBidListId());
        assertNotNull(bidListFound);
        assertEquals(bidListToFind.getAccount(), bidListFound.getAccount());
        assertEquals(bidListToFind.getType(), bidListFound.getType());
        assertEquals(bidListToFind.getBidQuantity(), bidListFound.getBidQuantity());
        assertEquals(bidListToFind.getAskQuantity(), bidListFound.getAskQuantity());
        assertEquals(bidListToFind.getBid(), bidListFound.getBid());
        assertEquals(bidListToFind.getAsk(), bidListFound.getAsk());
        assertEquals(bidListToFind.getBenchmark(), bidListFound.getBenchmark());
        assertEquals(bidListToFind.getBidListDate(), bidListFound.getBidListDate());
        assertEquals(bidListToFind.getCommentary(), bidListFound.getCommentary());
        assertEquals(bidListToFind.getSecurity(), bidListFound.getSecurity());
        assertEquals(bidListToFind.getStatus(), bidListFound.getStatus());
        assertEquals(bidListToFind.getTrader(), bidListFound.getTrader());
        assertEquals(bidListToFind.getBook(), bidListFound.getBook());
        assertEquals(bidListToFind.getCreationName(), bidListFound.getCreationName());
        assertEquals(bidListToFind.getCreationDate(), bidListFound.getCreationDate());
        assertEquals(bidListToFind.getRevisionName(), bidListFound.getRevisionName());
        assertEquals(bidListToFind.getRevisionDate(), bidListFound.getRevisionDate());
        assertEquals(bidListToFind.getDealName(), bidListFound.getDealName());
        assertEquals(bidListToFind.getDealType(), bidListFound.getDealType());
        assertEquals(bidListToFind.getSourceListId(), bidListFound.getSourceListId());
        assertEquals(bidListToFind.getSide(), bidListFound.getSide());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findAllBidLists() {
        // ARRANGE
        BidList bidListToFind1 = new BidList("Account", "Type", 10d);
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
        bidListServiceImplUnderTest.createBidList(bidListToFind1);

        BidList bidListToFind2 = new BidList("Account", "Type", 10d);
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
        bidListServiceImplUnderTest.createBidList(bidListToFind2);

        BidList bidListToFind3 = new BidList("Account", "Type", 10d);
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
        bidListServiceImplUnderTest.createBidList(bidListToFind3);

        // ACT
        List<BidList> listBidLists = bidListServiceImplUnderTest.findAllBidLists();

        // ASSERT
        assertEquals(3, listBidLists.size());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void deleteBidListById() {
        // ARRANGE
        BidList bidListToDelete = new BidList("Account", "Type", 10d);
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
        bidListToDelete = bidListServiceImplUnderTest.createBidList(bidListToDelete);

        // ACT
        Integer id = bidListToDelete.getBidListId();
        bidListServiceImplUnderTest.deleteBidListById(id);

        // ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            bidListServiceImplUnderTest.findBidListById(id);
        });
    }
}

