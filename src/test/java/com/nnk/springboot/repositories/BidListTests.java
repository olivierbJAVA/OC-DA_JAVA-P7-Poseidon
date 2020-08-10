package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.BidListRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Class including integration (with the database) tests for the
 * BidList Repository.
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class BidListTests {

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    public void bidListTests() {
        //BidList bidListTest = new BidList("Account Test", "Type Test", 10d);

        BidList bidListTest = new BidList();
        bidListTest.setAccount("Account Test");
        bidListTest.setType("Type Test");
        bidListTest.setBidQuantity(10d);
        bidListTest.setAskQuantity(30d);
        bidListTest.setBid(123.45d);
        bidListTest.setAsk(321.54d);
        bidListTest.setBenchmark("Benchmark Test");
        bidListTest.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest.setCommentary("Commentary Test");
        bidListTest.setSecurity("Security Test");
        bidListTest.setStatus("StatusTest");
        bidListTest.setTrader("Trader Test");
        bidListTest.setBook("Book Test");
        bidListTest.setCreationName("CreationName Test");
        bidListTest.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest.setRevisionName("RevisionName Test");
        bidListTest.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListTest.setDealName("DealName Test");
        bidListTest.setDealType("DealType Test");
        bidListTest.setSourceListId("SourceListId Test");
        bidListTest.setSide("Side Test");

        // Save
        bidListTest = bidListRepository.save(bidListTest);
        assertNotNull(bidListTest.getBidListId());
        assertEquals(bidListTest.getBidQuantity(), 10d, 10d);

        // Update
        bidListTest.setBidQuantity(20d);
        bidListTest = bidListRepository.save(bidListTest);
        assertEquals(bidListTest.getBidQuantity(), 20d, 20d);

        // Find by id
        Optional<BidList> bidListGet = bidListRepository.findById(bidListTest.getBidListId());
        assertTrue(bidListGet.isPresent());
        assertEquals(bidListTest.getAccount(), bidListGet.get().getAccount());
        assertEquals(bidListTest.getType(), bidListGet.get().getType());
        assertEquals(bidListTest.getBidQuantity(), bidListGet.get().getBidQuantity());
        assertEquals(bidListTest.getAskQuantity(), bidListGet.get().getAskQuantity());
        assertEquals(bidListTest.getBid(), bidListGet.get().getBid());
        assertEquals(bidListTest.getAsk(), bidListGet.get().getAsk());
        assertEquals(bidListTest.getBenchmark(), bidListGet.get().getBenchmark());
        assertEquals(bidListTest.getBidListDate(), bidListGet.get().getBidListDate());
        assertEquals(bidListTest.getCommentary(), bidListGet.get().getCommentary());
        assertEquals(bidListTest.getSecurity(), bidListGet.get().getSecurity());
        assertEquals(bidListTest.getStatus(), bidListGet.get().getStatus());
        assertEquals(bidListTest.getTrader(), bidListGet.get().getTrader());
        assertEquals(bidListTest.getBook(), bidListGet.get().getBook());
        assertEquals(bidListTest.getCreationName(), bidListGet.get().getCreationName());
        assertEquals(bidListTest.getCreationDate(), bidListGet.get().getCreationDate());
        assertEquals(bidListTest.getRevisionName(), bidListGet.get().getRevisionName());
        assertEquals(bidListTest.getRevisionDate(), bidListGet.get().getRevisionDate());
        assertEquals(bidListTest.getDealName(), bidListGet.get().getDealName());
        assertEquals(bidListTest.getDealType(), bidListGet.get().getDealType());
        assertEquals(bidListTest.getSourceListId(), bidListGet.get().getSourceListId());
        assertEquals(bidListTest.getSide(), bidListGet.get().getSide());

        // Find all
        BidList bidListTest2 = new BidList();
        bidListTest2.setAccount("Account Test");
        bidListTest2.setType("Type Test");
        bidListTest2.setBidQuantity(10d);
        bidListTest2.setAskQuantity(30d);
        bidListTest2.setBid(123.45d);
        bidListTest2.setAsk(321.54d);
        bidListTest2.setBenchmark("Benchmark Test");
        bidListTest2.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest2.setCommentary("Commentary Test");
        bidListTest2.setSecurity("Security Test");
        bidListTest2.setStatus("StatusTest");
        bidListTest2.setTrader("Trader Test");
        bidListTest2.setBook("Book Test");
        bidListTest2.setCreationName("CreationName Test");
        bidListTest2.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest2.setRevisionName("RevisionName Test");
        bidListTest2.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListTest2.setDealName("DealName Test");
        bidListTest2.setDealType("DealType Test");
        bidListTest2.setSourceListId("SourceListId Test");
        bidListTest2.setSide("Side Test");
        bidListRepository.save(bidListTest2);

        BidList bidListTest3 = new BidList();
        bidListTest3.setAccount("Account Test");
        bidListTest3.setType("Type Test");
        bidListTest3.setBidQuantity(10d);
        bidListTest3.setAskQuantity(30d);
        bidListTest3.setBid(123.45d);
        bidListTest3.setAsk(321.54d);
        bidListTest3.setBenchmark("Benchmark Test");
        bidListTest3.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest3.setCommentary("Commentary Test");
        bidListTest3.setSecurity("Security Test");
        bidListTest3.setStatus("StatusTest");
        bidListTest3.setTrader("Trader Test");
        bidListTest3.setBook("Book Test");
        bidListTest3.setCreationName("CreationName Test");
        bidListTest3.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest3.setRevisionName("RevisionName Test");
        bidListTest3.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListTest3.setDealName("DealName Test");
        bidListTest3.setDealType("DealType Test");
        bidListTest3.setSourceListId("SourceListId Test");
        bidListTest3.setSide("Side Test");
        bidListRepository.save(bidListTest3);

        List<BidList> listResult = bidListRepository.findAll();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = bidListTest.getBidListId();
        bidListRepository.delete(bidListTest);
        Optional<BidList> bidList = bidListRepository.findById(id);
        assertFalse(bidList.isPresent());
    }
}

