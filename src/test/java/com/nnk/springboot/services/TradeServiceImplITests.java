package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
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
 * Trade entity Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql("/schema-test.sql")
public class TradeServiceImplITests {

    @Autowired
    private TradeServiceImpl tradeServiceImplUnderTest;

    @Test
    @Sql("/cleandb-test.sql")
    public void createTrade() {
        // ARRANGE
        Trade tradeToCreate = new Trade("Trade Account", "Type", 1000d);
        tradeToCreate.setSellQuantity(100d);
        tradeToCreate.setBuyPrice(123.00d);
        tradeToCreate.setSellPrice(456.12d);
        tradeToCreate.setTradeDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToCreate.setSecurity("Security");
        tradeToCreate.setStatus("Status");
        tradeToCreate.setTrader("Trader");
        tradeToCreate.setBenchmark("Benchmark");
        tradeToCreate.setBook("Book");
        tradeToCreate.setCreationName("CreationName");
        tradeToCreate.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToCreate.setDealName("DealName");
        tradeToCreate.setDealType("DealType");
        tradeToCreate.setSourceListId("SourceListId");
        tradeToCreate.setSide("Side");

        // ACT
        Trade tradeCreated = tradeServiceImplUnderTest.createTrade(tradeToCreate);

        // ASSERT
        assertNotNull(tradeCreated.getTradeId());
        assertEquals(tradeToCreate.getAccount(), tradeCreated.getAccount());
        assertEquals(tradeToCreate.getType(), tradeCreated.getType());
        assertEquals(tradeToCreate.getBuyQuantity(), tradeCreated.getBuyQuantity());
        assertEquals(tradeToCreate.getSellQuantity(), tradeCreated.getSellQuantity());
        assertEquals(tradeToCreate.getBuyPrice(), tradeCreated.getBuyPrice());
        assertEquals(tradeToCreate.getSellPrice(), tradeCreated.getSellPrice());
        assertEquals(tradeToCreate.getTradeDate(), tradeCreated.getTradeDate());
        assertEquals(tradeToCreate.getSecurity(), tradeCreated.getSecurity());
        assertEquals(tradeToCreate.getStatus(), tradeCreated.getStatus());
        assertEquals(tradeToCreate.getTrader(), tradeCreated.getTrader());
        assertEquals(tradeToCreate.getBenchmark(), tradeCreated.getBenchmark());
        assertEquals(tradeToCreate.getBook(), tradeCreated.getBook());
        assertEquals(tradeToCreate.getCreationName(), tradeCreated.getCreationName());
        assertEquals(tradeToCreate.getCreationDate(), tradeCreated.getCreationDate());
        assertEquals(tradeToCreate.getRevisionName(), tradeCreated.getRevisionName());
        assertEquals(tradeToCreate.getRevisionDate(), tradeCreated.getRevisionDate());
        assertEquals(tradeToCreate.getDealName(), tradeCreated.getDealName());
        assertEquals(tradeToCreate.getDealType(), tradeCreated.getDealType());
        assertEquals(tradeToCreate.getSourceListId(), tradeCreated.getSourceListId());
        assertEquals(tradeToCreate.getSide(), tradeCreated.getSide());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void updateTrade() {
        // ARRANGE
        Trade tradeToUpdate = new Trade("Trade Account", "Type", 1000d);
        tradeToUpdate.setSellQuantity(100d);
        tradeToUpdate.setBuyPrice(123.00d);
        tradeToUpdate.setSellPrice(456.12d);
        tradeToUpdate.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToUpdate.setSecurity("Security");
        tradeToUpdate.setStatus("Status");
        tradeToUpdate.setTrader("Trader");
        tradeToUpdate.setBenchmark("Benchmark");
        tradeToUpdate.setBook("Book");
        tradeToUpdate.setCreationName("CreationName");
        tradeToUpdate.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToUpdate.setRevisionName("RevisionName");
        tradeToUpdate.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToUpdate.setDealName("DealName");
        tradeToUpdate.setDealType("DealType");
        tradeToUpdate.setSourceListId("SourceListId");
        tradeToUpdate.setSide("Side");
        tradeServiceImplUnderTest.createTrade(tradeToUpdate);

        // ACT
        tradeToUpdate.setAccount("Trade Account Update");
        Trade tradeUpdated = tradeServiceImplUnderTest.updateTrade(tradeToUpdate);

        // ASSERT
        assertEquals(tradeToUpdate.getAccount(), tradeUpdated.getAccount());
        assertEquals(tradeToUpdate.getType(), tradeUpdated.getType());
        assertEquals(tradeToUpdate.getBuyQuantity(), tradeUpdated.getBuyQuantity());
        assertEquals(tradeToUpdate.getSellQuantity(), tradeUpdated.getSellQuantity());
        assertEquals(tradeToUpdate.getBuyPrice(), tradeUpdated.getBuyPrice());
        assertEquals(tradeToUpdate.getSellPrice(), tradeUpdated.getSellPrice());
        assertEquals(tradeToUpdate.getTradeDate(), tradeUpdated.getTradeDate());
        assertEquals(tradeToUpdate.getSecurity(), tradeUpdated.getSecurity());
        assertEquals(tradeToUpdate.getStatus(), tradeUpdated.getStatus());
        assertEquals(tradeToUpdate.getTrader(), tradeUpdated.getTrader());
        assertEquals(tradeToUpdate.getBenchmark(), tradeUpdated.getBenchmark());
        assertEquals(tradeToUpdate.getBook(), tradeUpdated.getBook());
        assertEquals(tradeToUpdate.getCreationName(), tradeUpdated.getCreationName());
        assertEquals(tradeToUpdate.getCreationDate(), tradeUpdated.getCreationDate());
        assertEquals(tradeToUpdate.getRevisionName(), tradeUpdated.getRevisionName());
        assertEquals(tradeToUpdate.getRevisionDate(), tradeUpdated.getRevisionDate());
        assertEquals(tradeToUpdate.getDealName(), tradeUpdated.getDealName());
        assertEquals(tradeToUpdate.getDealType(), tradeUpdated.getDealType());
        assertEquals(tradeToUpdate.getSourceListId(), tradeUpdated.getSourceListId());
        assertEquals(tradeToUpdate.getSide(), tradeUpdated.getSide());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findTradeById() {
        // ARRANGE
        Trade tradeToFind = new Trade("Trade Account", "Type", 1000d);
        tradeToFind.setSellQuantity(100d);
        tradeToFind.setBuyPrice(123.00d);
        tradeToFind.setSellPrice(456.12d);
        tradeToFind.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToFind.setSecurity("Security");
        tradeToFind.setStatus("Status");
        tradeToFind.setTrader("Trader");
        tradeToFind.setBenchmark("Benchmark");
        tradeToFind.setBook("Book");
        tradeToFind.setCreationName("CreationName");
        tradeToFind.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToFind.setRevisionName("RevisionName");
        tradeToFind.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToFind.setDealName("DealName");
        tradeToFind.setDealType("DealType");
        tradeToFind.setSourceListId("SourceListId");
        tradeToFind.setSide("Side");
        tradeToFind = tradeServiceImplUnderTest.createTrade(tradeToFind);

        // ACT
        Trade tradeFound = tradeServiceImplUnderTest.findTradeById(tradeToFind.getTradeId());

        // ASSERT
        assertNotNull(tradeFound);
        assertEquals(tradeToFind.getAccount(), tradeFound.getAccount());
        assertEquals(tradeToFind.getType(), tradeFound.getType());
        assertEquals(tradeToFind.getBuyQuantity(), tradeFound.getBuyQuantity());
        assertEquals(tradeToFind.getSellQuantity(), tradeFound.getSellQuantity());
        assertEquals(tradeToFind.getBuyPrice(), tradeFound.getBuyPrice());
        assertEquals(tradeToFind.getSellPrice(), tradeFound.getSellPrice());
        assertEquals(tradeToFind.getTradeDate(), tradeFound.getTradeDate());
        assertEquals(tradeToFind.getSecurity(), tradeFound.getSecurity());
        assertEquals(tradeToFind.getStatus(), tradeFound.getStatus());
        assertEquals(tradeToFind.getTrader(), tradeFound.getTrader());
        assertEquals(tradeToFind.getBenchmark(), tradeFound.getBenchmark());
        assertEquals(tradeToFind.getBook(), tradeFound.getBook());
        assertEquals(tradeToFind.getCreationName(), tradeFound.getCreationName());
        assertEquals(tradeToFind.getCreationDate(), tradeFound.getCreationDate());
        assertEquals(tradeToFind.getRevisionName(), tradeFound.getRevisionName());
        assertEquals(tradeToFind.getRevisionDate(), tradeFound.getRevisionDate());
        assertEquals(tradeToFind.getDealName(), tradeFound.getDealName());
        assertEquals(tradeToFind.getDealType(), tradeFound.getDealType());
        assertEquals(tradeToFind.getSourceListId(), tradeFound.getSourceListId());
        assertEquals(tradeToFind.getSide(), tradeFound.getSide());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findAllTrades() {
        Trade tradeToFind1 = new Trade("Trade Account", "Type", 1000d);
        tradeToFind1.setSellQuantity(100d);
        tradeToFind1.setBuyPrice(123.00d);
        tradeToFind1.setSellPrice(456.12d);
        tradeToFind1.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToFind1.setSecurity("Security");
        tradeToFind1.setStatus("Status");
        tradeToFind1.setTrader("Trader");
        tradeToFind1.setBenchmark("Benchmark");
        tradeToFind1.setBook("Book");
        tradeToFind1.setCreationName("CreationName");
        tradeToFind1.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToFind1.setRevisionName("RevisionName");
        tradeToFind1.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToFind1.setDealName("DealName");
        tradeToFind1.setDealType("DealType");
        tradeToFind1.setSourceListId("SourceListId");
        tradeToFind1.setSide("Side");
        tradeServiceImplUnderTest.createTrade(tradeToFind1);

        Trade tradeToFind2 = new Trade("Trade Account", "Type", 1000d);
        tradeToFind2.setSellQuantity(100d);
        tradeToFind2.setBuyPrice(123.00d);
        tradeToFind2.setSellPrice(456.12d);
        tradeToFind2.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToFind2.setSecurity("Security");
        tradeToFind2.setStatus("Status");
        tradeToFind2.setTrader("Trader");
        tradeToFind2.setBenchmark("Benchmark");
        tradeToFind2.setBook("Book");
        tradeToFind2.setCreationName("CreationName");
        tradeToFind2.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToFind2.setRevisionName("RevisionName");
        tradeToFind2.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToFind2.setDealName("DealName");
        tradeToFind2.setDealType("DealType");
        tradeToFind2.setSourceListId("SourceListId");
        tradeToFind2.setSide("Side");
        tradeServiceImplUnderTest.createTrade(tradeToFind2);

        Trade tradeToFind3 = new Trade("Trade Account", "Type", 1000d);
        tradeToFind3.setSellQuantity(100d);
        tradeToFind3.setBuyPrice(123.00d);
        tradeToFind3.setSellPrice(456.12d);
        tradeToFind3.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToFind3.setSecurity("Security");
        tradeToFind3.setStatus("Status");
        tradeToFind3.setTrader("Trader");
        tradeToFind3.setBenchmark("Benchmark");
        tradeToFind3.setBook("Book");
        tradeToFind3.setCreationName("CreationName");
        tradeToFind3.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToFind3.setRevisionName("RevisionName");
        tradeToFind3.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToFind3.setDealName("DealName");
        tradeToFind3.setDealType("DealType");
        tradeToFind3.setSourceListId("SourceListId");
        tradeToFind3.setSide("Side");
        tradeServiceImplUnderTest.createTrade(tradeToFind3);

        // ACT
        List<Trade> listTrades = tradeServiceImplUnderTest.findAllTrades();

        // ASSERT
        assertEquals(3, listTrades.size());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void deleteTradeById() {
        // ARRANGE
        Trade tradeToDelete = new Trade("Trade Account", "Type", 1000d);
        tradeToDelete.setSellQuantity(100d);
        tradeToDelete.setBuyPrice(123.00d);
        tradeToDelete.setSellPrice(456.12d);
        tradeToDelete.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToDelete.setSecurity("Security");
        tradeToDelete.setStatus("Status");
        tradeToDelete.setTrader("Trader");
        tradeToDelete.setBenchmark("Benchmark");
        tradeToDelete.setBook("Book");
        tradeToDelete.setCreationName("CreationName");
        tradeToDelete.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToDelete.setRevisionName("RevisionName");
        tradeToDelete.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToDelete.setDealName("DealName");
        tradeToDelete.setDealType("DealType");
        tradeToDelete.setSourceListId("SourceListId");
        tradeToDelete.setSide("Side");
        tradeToDelete = tradeServiceImplUnderTest.createTrade(tradeToDelete);

        // ACT
        Integer id = tradeToDelete.getTradeId();
        tradeServiceImplUnderTest.deleteTradeById(id);

        // ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            tradeServiceImplUnderTest.findTradeById(id);
        });
    }
}
