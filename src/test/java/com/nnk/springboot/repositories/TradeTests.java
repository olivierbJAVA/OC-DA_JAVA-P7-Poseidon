package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;

import java.time.LocalDateTime;
import java.sql.Timestamp;

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

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class TradeTests {

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
        tradeTest.setTradeDate(Timestamp.valueOf(LocalDateTime.now()));
        tradeTest.setSecurity("Security");
        tradeTest.setStatus("Status");
        tradeTest.setTrader("Trader");
        tradeTest.setBenchmark("Benchmark");
        tradeTest.setBook("Book");
        tradeTest.setCreationName("CreationName");
        tradeTest.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        tradeTest.setRevisionName("RevisionName");
        tradeTest.setRevisionDate(Timestamp.valueOf(LocalDateTime.now()));
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
        List<Trade> listResult = tradeRepositoryUnderTest.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = tradeTest.getTradeId();
        tradeRepositoryUnderTest.delete(tradeTest);
        Optional<Trade> tradeList = tradeRepositoryUnderTest.findById(id);
        assertFalse(tradeList.isPresent());
    }
}
