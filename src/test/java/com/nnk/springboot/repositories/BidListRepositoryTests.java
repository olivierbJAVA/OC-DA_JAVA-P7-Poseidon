package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;

import org.junit.jupiter.api.Test;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

/**
 * Class including integration (with the database) tests for the
 * BidList Repository.
 */
//@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class BidListRepositoryTests {

    @Autowired
    private BidListRepository bidListRepositoryUnderTest;

    @Test
    public void saveBidList() {
        // ARRANGE
        BidList bidListToSave = new BidList("Account Test", "Type Test", 10d);
        bidListToSave.setAskQuantity(30d);
        bidListToSave.setBid(123.45d);
        bidListToSave.setAsk(321.54d);
        bidListToSave.setBenchmark("Benchmark Test");
        bidListToSave.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListToSave.setCommentary("Commentary Test");
        bidListToSave.setSecurity("Security Test");
        bidListToSave.setStatus("StatusTest");
        bidListToSave.setTrader("Trader Test");
        bidListToSave.setBook("Book Test");
        bidListToSave.setCreationName("CreationName Test");
        bidListToSave.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListToSave.setRevisionName("RevisionName Test");
        bidListToSave.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListToSave.setDealName("DealName Test");
        bidListToSave.setDealType("DealType Test");
        bidListToSave.setSourceListId("SourceListId Test");
        bidListToSave.setSide("Side Test");

        // ACT
        BidList bidListSaved = bidListRepositoryUnderTest.save(bidListToSave);
        
        // ASSERT
        assertNotNull(bidListSaved.getBidListId());
        assertEquals(bidListToSave.getAccount(), bidListSaved.getAccount());
        assertEquals(bidListToSave.getType(), bidListSaved.getType());
        assertEquals(bidListToSave.getBidQuantity(), bidListSaved.getBidQuantity());
        assertEquals(bidListToSave.getAskQuantity(), bidListSaved.getAskQuantity());
        assertEquals(bidListToSave.getBid(), bidListSaved.getBid());
        assertEquals(bidListToSave.getAsk(), bidListSaved.getAsk());
        assertEquals(bidListToSave.getBenchmark(), bidListSaved.getBenchmark());
        assertEquals(bidListToSave.getBidListDate(), bidListSaved.getBidListDate());
        assertEquals(bidListToSave.getCommentary(), bidListSaved.getCommentary());
        assertEquals(bidListToSave.getSecurity(), bidListSaved.getSecurity());
        assertEquals(bidListToSave.getStatus(), bidListSaved.getStatus());
        assertEquals(bidListToSave.getTrader(), bidListSaved.getTrader());
        assertEquals(bidListToSave.getBook(), bidListSaved.getBook());
        assertEquals(bidListToSave.getCreationName(), bidListSaved.getCreationName());
        assertEquals(bidListToSave.getCreationDate(), bidListSaved.getCreationDate());
        assertEquals(bidListToSave.getRevisionName(), bidListSaved.getRevisionName());
        assertEquals(bidListToSave.getRevisionDate(), bidListSaved.getRevisionDate());
        assertEquals(bidListToSave.getDealName(), bidListSaved.getDealName());
        assertEquals(bidListToSave.getDealType(), bidListSaved.getDealType());
        assertEquals(bidListToSave.getSourceListId(), bidListSaved.getSourceListId());
        assertEquals(bidListToSave.getSide(), bidListSaved.getSide());
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
        bidListRepositoryUnderTest.save(bidListToUpdate);

        // ACT
        bidListToUpdate.setBidQuantity(20d);
        BidList bidListUpdated = bidListRepositoryUnderTest.save(bidListToUpdate);
        
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
    public void findBidListById() {
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
        bidListToFind = bidListRepositoryUnderTest.save(bidListToFind);

        // ACT
        Optional<BidList> bidListFound = bidListRepositoryUnderTest.findById(bidListToFind.getBidListId());
        assertTrue(bidListFound.isPresent());
        assertEquals(bidListToFind.getAccount(), bidListFound.get().getAccount());
        assertEquals(bidListToFind.getType(), bidListFound.get().getType());
        assertEquals(bidListToFind.getBidQuantity(), bidListFound.get().getBidQuantity());
        assertEquals(bidListToFind.getAskQuantity(), bidListFound.get().getAskQuantity());
        assertEquals(bidListToFind.getBid(), bidListFound.get().getBid());
        assertEquals(bidListToFind.getAsk(), bidListFound.get().getAsk());
        assertEquals(bidListToFind.getBenchmark(), bidListFound.get().getBenchmark());
        assertEquals(bidListToFind.getBidListDate(), bidListFound.get().getBidListDate());
        assertEquals(bidListToFind.getCommentary(), bidListFound.get().getCommentary());
        assertEquals(bidListToFind.getSecurity(), bidListFound.get().getSecurity());
        assertEquals(bidListToFind.getStatus(), bidListFound.get().getStatus());
        assertEquals(bidListToFind.getTrader(), bidListFound.get().getTrader());
        assertEquals(bidListToFind.getBook(), bidListFound.get().getBook());
        assertEquals(bidListToFind.getCreationName(), bidListFound.get().getCreationName());
        assertEquals(bidListToFind.getCreationDate(), bidListFound.get().getCreationDate());
        assertEquals(bidListToFind.getRevisionName(), bidListFound.get().getRevisionName());
        assertEquals(bidListToFind.getRevisionDate(), bidListFound.get().getRevisionDate());
        assertEquals(bidListToFind.getDealName(), bidListFound.get().getDealName());
        assertEquals(bidListToFind.getDealType(), bidListFound.get().getDealType());
        assertEquals(bidListToFind.getSourceListId(), bidListFound.get().getSourceListId());
        assertEquals(bidListToFind.getSide(), bidListFound.get().getSide());
    }

    @Test
    public void findAllBidLists() {
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
        bidListRepositoryUnderTest.save(bidListToFind1);

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
        bidListRepositoryUnderTest.save(bidListToFind2);

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
        bidListRepositoryUnderTest.save(bidListToFind3);

        // ACT
        List<BidList> listBidLists = bidListRepositoryUnderTest.findAll();

        // ASSERT
        assertTrue(listBidLists.size() == 3);
    }

    @Test
    public void deleteBidListById() {
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
        bidListToDelete = bidListRepositoryUnderTest.save(bidListToDelete);

        // ACT
        Integer id = bidListToDelete.getBidListId();
        bidListRepositoryUnderTest.deleteById(id);

        // ASSERT
        Optional<BidList> bidListDeleted = bidListRepositoryUnderTest.findById(id);
        assertFalse(bidListDeleted.isPresent());
    }
}

