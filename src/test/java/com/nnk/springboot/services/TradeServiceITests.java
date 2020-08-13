package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the database) tests for the
 * Trade Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql({"/schema-test.sql"})
public class TradeServiceITests {

    @Autowired
    private TradeImplService tradeImplServiceUnderTest;

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
        tradeTest = tradeImplServiceUnderTest.createTrade(tradeTest);
        assertNotNull(tradeTest.getTradeId());
        assertTrue(tradeTest.getAccount().equals("Trade Account"));

        // Update
        tradeTest.setAccount("Trade Account Update");
        tradeTest = tradeImplServiceUnderTest.updateTrade(tradeTest);
        assertTrue(tradeTest.getAccount().equals("Trade Account Update"));

        // Find by id
        Trade tradeGet = tradeImplServiceUnderTest.findTradeById(tradeTest.getTradeId());
        assertNotNull(tradeGet);
        assertEquals(tradeTest.getAccount(), tradeGet.getAccount());
        assertEquals(tradeTest.getType(), tradeGet.getType());
        assertEquals(tradeTest.getBuyQuantity(), tradeGet.getBuyQuantity());
        assertEquals(tradeTest.getSellQuantity(), tradeGet.getSellQuantity());
        assertEquals(tradeTest.getBuyPrice(), tradeGet.getBuyPrice());
        assertEquals(tradeTest.getSellPrice(), tradeGet.getSellPrice());
        assertEquals(tradeTest.getTradeDate(), tradeGet.getTradeDate());
        assertEquals(tradeTest.getSecurity(), tradeGet.getSecurity());
        assertEquals(tradeTest.getStatus(), tradeGet.getStatus());
        assertEquals(tradeTest.getTrader(), tradeGet.getTrader());
        assertEquals(tradeTest.getBenchmark(), tradeGet.getBenchmark());
        assertEquals(tradeTest.getBook(), tradeGet.getBook());
        assertEquals(tradeTest.getCreationName(), tradeGet.getCreationName());
        assertEquals(tradeTest.getCreationDate(), tradeGet.getCreationDate());
        assertEquals(tradeTest.getRevisionName(), tradeGet.getRevisionName());
        assertEquals(tradeTest.getRevisionDate(), tradeGet.getRevisionDate());
        assertEquals(tradeTest.getDealName(), tradeGet.getDealName());
        assertEquals(tradeTest.getDealType(), tradeGet.getDealType());
        assertEquals(tradeTest.getSourceListId(), tradeGet.getSourceListId());
        assertEquals(tradeTest.getSide(), tradeGet.getSide());

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

        tradeImplServiceUnderTest.createTrade(tradeTest2);

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

        tradeImplServiceUnderTest.createTrade(tradeTest3);

        List<Trade> listResult = tradeImplServiceUnderTest.findAllTrades();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = tradeTest.getTradeId();
        tradeImplServiceUnderTest.deleteTradeById(id);
        assertThrows(RecordNotFoundException.class, () -> {
            tradeImplServiceUnderTest.findTradeById(id);
        });
    }
}
