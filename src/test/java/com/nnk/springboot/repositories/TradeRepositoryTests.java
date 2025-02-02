package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

/**
 * Class including unit tests for the Trade entity Repository.
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class TradeRepositoryTests {

    @Autowired
    private TradeRepository tradeRepositoryUnderTest;

    @Test
    public void saveTrade() {
        // ARRANGE
        Trade tradeToSave = new Trade("Trade Account", "Type", 1000d);
        tradeToSave.setSellQuantity(100d);
        tradeToSave.setBuyPrice(123.00d);
        tradeToSave.setSellPrice(456.12d);
        tradeToSave.setTradeDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToSave.setSecurity("Security");
        tradeToSave.setStatus("Status");
        tradeToSave.setTrader("Trader");
        tradeToSave.setBenchmark("Benchmark");
        tradeToSave.setBook("Book");
        tradeToSave.setCreationName("CreationName");
        tradeToSave.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToSave.setDealName("DealName");
        tradeToSave.setDealType("DealType");
        tradeToSave.setSourceListId("SourceListId");
        tradeToSave.setSide("Side");

        // ACT
        Trade tradeSaved = tradeRepositoryUnderTest.save(tradeToSave);

        // ASSERT
        assertNotNull(tradeSaved.getTradeId());
        assertEquals(tradeToSave.getAccount(), tradeSaved.getAccount());
        assertEquals(tradeToSave.getType(), tradeSaved.getType());
        assertEquals(tradeToSave.getBuyQuantity(), tradeSaved.getBuyQuantity());
        assertEquals(tradeToSave.getSellQuantity(), tradeSaved.getSellQuantity());
        assertEquals(tradeToSave.getBuyPrice(), tradeSaved.getBuyPrice());
        assertEquals(tradeToSave.getSellPrice(), tradeSaved.getSellPrice());
        assertEquals(tradeToSave.getTradeDate(), tradeSaved.getTradeDate());
        assertEquals(tradeToSave.getSecurity(), tradeSaved.getSecurity());
        assertEquals(tradeToSave.getStatus(), tradeSaved.getStatus());
        assertEquals(tradeToSave.getTrader(), tradeSaved.getTrader());
        assertEquals(tradeToSave.getBenchmark(), tradeSaved.getBenchmark());
        assertEquals(tradeToSave.getBook(), tradeSaved.getBook());
        assertEquals(tradeToSave.getCreationName(), tradeSaved.getCreationName());
        assertEquals(tradeToSave.getCreationDate(), tradeSaved.getCreationDate());
        assertEquals(tradeToSave.getRevisionName(), tradeSaved.getRevisionName());
        assertEquals(tradeToSave.getRevisionDate(), tradeSaved.getRevisionDate());
        assertEquals(tradeToSave.getDealName(), tradeSaved.getDealName());
        assertEquals(tradeToSave.getDealType(), tradeSaved.getDealType());
        assertEquals(tradeToSave.getSourceListId(), tradeSaved.getSourceListId());
        assertEquals(tradeToSave.getSide(), tradeSaved.getSide());
    }

    @Test
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
        tradeRepositoryUnderTest.save(tradeToUpdate);

        // ACT
        tradeToUpdate.setAccount("Trade Account Update");
        Trade tradeUpdated = tradeRepositoryUnderTest.save(tradeToUpdate);

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
        tradeToFind = tradeRepositoryUnderTest.save(tradeToFind);

        // ACT
        Optional<Trade> tradeFound = tradeRepositoryUnderTest.findById(tradeToFind.getTradeId());

        // ASSERT
        assertTrue(tradeFound.isPresent());
        assertEquals(tradeToFind.getAccount(), tradeFound.get().getAccount());
        assertEquals(tradeToFind.getType(), tradeFound.get().getType());
        assertEquals(tradeToFind.getBuyQuantity(), tradeFound.get().getBuyQuantity());
        assertEquals(tradeToFind.getSellQuantity(), tradeFound.get().getSellQuantity());
        assertEquals(tradeToFind.getBuyPrice(), tradeFound.get().getBuyPrice());
        assertEquals(tradeToFind.getSellPrice(), tradeFound.get().getSellPrice());
        assertEquals(tradeToFind.getTradeDate(), tradeFound.get().getTradeDate());
        assertEquals(tradeToFind.getSecurity(), tradeFound.get().getSecurity());
        assertEquals(tradeToFind.getStatus(), tradeFound.get().getStatus());
        assertEquals(tradeToFind.getTrader(), tradeFound.get().getTrader());
        assertEquals(tradeToFind.getBenchmark(), tradeFound.get().getBenchmark());
        assertEquals(tradeToFind.getBook(), tradeFound.get().getBook());
        assertEquals(tradeToFind.getCreationName(), tradeFound.get().getCreationName());
        assertEquals(tradeToFind.getCreationDate(), tradeFound.get().getCreationDate());
        assertEquals(tradeToFind.getRevisionName(), tradeFound.get().getRevisionName());
        assertEquals(tradeToFind.getRevisionDate(), tradeFound.get().getRevisionDate());
        assertEquals(tradeToFind.getDealName(), tradeFound.get().getDealName());
        assertEquals(tradeToFind.getDealType(), tradeFound.get().getDealType());
        assertEquals(tradeToFind.getSourceListId(), tradeFound.get().getSourceListId());
        assertEquals(tradeToFind.getSide(), tradeFound.get().getSide());
    }

    @Test
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
        tradeRepositoryUnderTest.save(tradeToFind1);

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
        tradeRepositoryUnderTest.save(tradeToFind2);

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
        tradeRepositoryUnderTest.save(tradeToFind3);

        // ACT
        List<Trade> listTrades = tradeRepositoryUnderTest.findAll();

        // ASSERT
        assertEquals(3, listTrades.size());
    }

    @Test
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
        tradeToDelete = tradeRepositoryUnderTest.save(tradeToDelete);

        // ACT
        Integer id = tradeToDelete.getTradeId();
        tradeRepositoryUnderTest.deleteById(id);

        // ASSERT
        Optional<Trade> tradeDeleted = tradeRepositoryUnderTest.findById(id);
        assertFalse(tradeDeleted.isPresent());
    }
}
