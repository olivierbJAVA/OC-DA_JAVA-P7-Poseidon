package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;

import java.time.LocalDateTime;
import java.sql.Timestamp;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.nnk.springboot.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

/**
 * Class including integration (with the database) tests for the
 * Trade Repository.
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class TradeRepositoryTests {

    @Autowired
    private TradeRepository tradeRepositoryUnderTest;

    @Test
    public void tradeTests() {
        //Trade tradeTest = new Trade("Trade Account", "Type");

        Trade tradeTest = new Trade();
        tradeTest.setAccount("Trade Account");
        tradeTest.setType("Type");
        tradeTest.setBuyQuantity(1000d);
        tradeTest.setSellQuantity(100d);
        tradeTest.setBuyPrice(123.00d);
        tradeTest.setSellPrice(456.12d);
        tradeTest.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeTest.setSecurity("Security");
        tradeTest.setStatus("Status");
        tradeTest.setTrader("Trader");
        tradeTest.setBenchmark("Benchmark");
        tradeTest.setBook("Book");
        tradeTest.setCreationName("CreationName");
        tradeTest.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeTest.setRevisionName("RevisionName");
        tradeTest.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeTest.setDealName("DealName");
        tradeTest.setDealType("DealType");
        tradeTest.setSourceListId("SourceListId");
        tradeTest.setSide("Side");

        // Save
        tradeTest = tradeRepositoryUnderTest.save(tradeTest);
        assertNotNull(tradeTest.getTradeId());
        assertTrue(tradeTest.getAccount().equals("Trade Account"));

        // Update
        tradeTest.setAccount("Trade Account Update");
        tradeTest = tradeRepositoryUnderTest.save(tradeTest);
        assertTrue(tradeTest.getAccount().equals("Trade Account Update"));

        // Find by id
        Optional<Trade> tradeGet = tradeRepositoryUnderTest.findById(tradeTest.getTradeId());
        assertTrue(tradeGet.isPresent());
        assertEquals(tradeTest.getAccount(), tradeGet.get().getAccount());
        assertEquals(tradeTest.getType(), tradeGet.get().getType());
        assertEquals(tradeTest.getBuyQuantity(), tradeGet.get().getBuyQuantity());
        assertEquals(tradeTest.getSellQuantity(), tradeGet.get().getSellQuantity());
        assertEquals(tradeTest.getBuyPrice(), tradeGet.get().getBuyPrice());
        assertEquals(tradeTest.getSellPrice(), tradeGet.get().getSellPrice());
        assertEquals(tradeTest.getTradeDate(), tradeGet.get().getTradeDate());
        assertEquals(tradeTest.getSecurity(), tradeGet.get().getSecurity());
        assertEquals(tradeTest.getStatus(), tradeGet.get().getStatus());
        assertEquals(tradeTest.getTrader(), tradeGet.get().getTrader());
        assertEquals(tradeTest.getBenchmark(), tradeGet.get().getBenchmark());
        assertEquals(tradeTest.getBook(), tradeGet.get().getBook());
        assertEquals(tradeTest.getCreationName(), tradeGet.get().getCreationName());
        assertEquals(tradeTest.getCreationDate(), tradeGet.get().getCreationDate());
        assertEquals(tradeTest.getRevisionName(), tradeGet.get().getRevisionName());
        assertEquals(tradeTest.getRevisionDate(), tradeGet.get().getRevisionDate());
        assertEquals(tradeTest.getDealName(), tradeGet.get().getDealName());
        assertEquals(tradeTest.getDealType(), tradeGet.get().getDealType());
        assertEquals(tradeTest.getSourceListId(), tradeGet.get().getSourceListId());
        assertEquals(tradeTest.getSide(), tradeGet.get().getSide());

        // Find all
        Trade tradeTest2 = new Trade();
        tradeTest2.setAccount("Trade Account");
        tradeTest2.setType("Type");
        tradeTest2.setBuyQuantity(1000d);
        tradeTest2.setSellQuantity(100d);
        tradeTest2.setBuyPrice(123.00d);
        tradeTest2.setSellPrice(456.12d);
        tradeTest2.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeTest2.setSecurity("Security");
        tradeTest2.setStatus("Status");
        tradeTest2.setTrader("Trader");
        tradeTest2.setBenchmark("Benchmark");
        tradeTest2.setBook("Book");
        tradeTest2.setCreationName("CreationName");
        tradeTest2.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeTest2.setRevisionName("RevisionName");
        tradeTest2.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeTest2.setDealName("DealName");
        tradeTest2.setDealType("DealType");
        tradeTest2.setSourceListId("SourceListId");
        tradeTest2.setSide("Side");

        tradeRepositoryUnderTest.save(tradeTest2);

        Trade tradeTest3 = new Trade();
        tradeTest3.setAccount("Trade Account");
        tradeTest3.setType("Type");
        tradeTest3.setBuyQuantity(1000d);
        tradeTest3.setSellQuantity(100d);
        tradeTest3.setBuyPrice(123.00d);
        tradeTest3.setSellPrice(456.12d);
        tradeTest3.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeTest3.setSecurity("Security");
        tradeTest3.setStatus("Status");
        tradeTest3.setTrader("Trader");
        tradeTest3.setBenchmark("Benchmark");
        tradeTest3.setBook("Book");
        tradeTest3.setCreationName("CreationName");
        tradeTest3.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeTest3.setRevisionName("RevisionName");
        tradeTest3.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeTest3.setDealName("DealName");
        tradeTest3.setDealType("DealType");
        tradeTest3.setSourceListId("SourceListId");
        tradeTest3.setSide("Side");

        tradeRepositoryUnderTest.save(tradeTest3);

        List<Trade> listResult = tradeRepositoryUnderTest.findAll();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = tradeTest.getTradeId();
        //tradeRepositoryUnderTest.delete(tradeTest);
        tradeRepositoryUnderTest.deleteById(id);
        Optional<Trade> tradeList = tradeRepositoryUnderTest.findById(id);
        assertFalse(tradeList.isPresent());
    }
}
