package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the database) tests for the
 * BidList Service.
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest//(classes={com.nnk.springboot.repositories.BidListRepository.class, com.nnk.springboot.services.BidListImplService.class})
//@ContextConfiguration(classes = {BidListRepository.class, BidListImplService.class})
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class BidListServiceITests {

    @Autowired
    private BidListImplService bidListImplServiceUnderTest;

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
        bidListTest = bidListImplServiceUnderTest.createBidList(bidListTest);
        assertNotNull(bidListTest.getBidListId());
        assertEquals(bidListTest.getBidQuantity(), 10d, 10d);

        // Update
        bidListTest.setBidQuantity(20d);
        bidListTest = bidListImplServiceUnderTest.updateBidList(bidListTest);
        assertEquals(bidListTest.getBidQuantity(), 20d, 20d);

        // Find by id
        BidList bidListGet = bidListImplServiceUnderTest.findBidListById(bidListTest.getBidListId());
        assertNotNull(bidListGet);
        assertEquals(bidListTest.getAccount(), bidListGet.getAccount());
        assertEquals(bidListTest.getType(), bidListGet.getType());
        assertEquals(bidListTest.getBidQuantity(), bidListGet.getBidQuantity());
        assertEquals(bidListTest.getAskQuantity(), bidListGet.getAskQuantity());
        assertEquals(bidListTest.getBid(), bidListGet.getBid());
        assertEquals(bidListTest.getAsk(), bidListGet.getAsk());
        assertEquals(bidListTest.getBenchmark(), bidListGet.getBenchmark());
        assertEquals(bidListTest.getBidListDate(), bidListGet.getBidListDate());
        assertEquals(bidListTest.getCommentary(), bidListGet.getCommentary());
        assertEquals(bidListTest.getSecurity(), bidListGet.getSecurity());
        assertEquals(bidListTest.getStatus(), bidListGet.getStatus());
        assertEquals(bidListTest.getTrader(), bidListGet.getTrader());
        assertEquals(bidListTest.getBook(), bidListGet.getBook());
        assertEquals(bidListTest.getCreationName(), bidListGet.getCreationName());
        assertEquals(bidListTest.getCreationDate(), bidListGet.getCreationDate());
        assertEquals(bidListTest.getRevisionName(), bidListGet.getRevisionName());
        assertEquals(bidListTest.getRevisionDate(), bidListGet.getRevisionDate());
        assertEquals(bidListTest.getDealName(), bidListGet.getDealName());
        assertEquals(bidListTest.getDealType(), bidListGet.getDealType());
        assertEquals(bidListTest.getSourceListId(), bidListGet.getSourceListId());
        assertEquals(bidListTest.getSide(), bidListGet.getSide());

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
        bidListImplServiceUnderTest.createBidList(bidListTest2);

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
        bidListImplServiceUnderTest.createBidList(bidListTest3);

        List<BidList> listResult = bidListImplServiceUnderTest.findAllBidLists();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = bidListTest.getBidListId();
        bidListImplServiceUnderTest.deleteBidListById(id);
        assertThrows(RecordNotFoundException.class, () -> {
            bidListImplServiceUnderTest.findBidListById(id);
        });
    }
}

