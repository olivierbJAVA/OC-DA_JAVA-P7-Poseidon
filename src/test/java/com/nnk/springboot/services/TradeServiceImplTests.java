package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Timestamp.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class including unit tests for the TradeServiceImpl Class.
 */
@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTests {

    @InjectMocks
    private TradeServiceImpl tradeServiceImplUnderTest;

    @Mock
    private TradeRepository mockTradeRepository;

    @Test
    public void createRating() {
        // ARRANGE
        Trade tradeToCreate = new Trade("Trade Account", "Type", 1000d);
        tradeToCreate.setTradeId(1);
        tradeToCreate.setSellQuantity(100d);
        tradeToCreate.setBuyPrice(123.00d);
        tradeToCreate.setSellPrice(456.12d);
        tradeToCreate.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeToCreate.setSecurity("Security");
        tradeToCreate.setStatus("Status");
        tradeToCreate.setTrader("Trader");
        tradeToCreate.setBenchmark("Benchmark");
        tradeToCreate.setBook("Book");
        tradeToCreate.setCreationName("CreationName");
        tradeToCreate.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeToCreate.setRevisionName("RevisionName");
        tradeToCreate.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeToCreate.setDealName("DealName");
        tradeToCreate.setDealType("DealType");
        tradeToCreate.setSourceListId("SourceListId");
        tradeToCreate.setSide("Side");
        doReturn(tradeToCreate).when(mockTradeRepository).save(tradeToCreate);

        // ACT
        Trade tradeCreated = tradeServiceImplUnderTest.createTrade(tradeToCreate);

        // ASSERT
        verify(mockTradeRepository, times(1)).save(tradeToCreate);
        assertEquals(tradeToCreate, tradeCreated);
    }

    @Test
    public void updateTrade_whenIdExist() {
        // ARRANGE
        Trade tradeToUpdate = new Trade("Trade Account", "Type", 1000d);
        tradeToUpdate.setTradeId(1);
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
        tradeToUpdate.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeToUpdate.setDealName("DealName");
        tradeToUpdate.setDealType("DealType");
        tradeToUpdate.setSourceListId("SourceListId");
        tradeToUpdate.setSide("Side");
        doReturn(Optional.of(tradeToUpdate)).when(mockTradeRepository).findById(tradeToUpdate.getTradeId());
        doReturn(tradeToUpdate).when(mockTradeRepository).save(tradeToUpdate);

        // ACT
        Trade tradeUpdated = tradeServiceImplUnderTest.updateTrade(tradeToUpdate);

        // ASSERT
        verify(mockTradeRepository, times(1)).save(tradeToUpdate);
        assertEquals(tradeToUpdate, tradeUpdated);
    }

    @Test
    public void updateTrade_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockTradeRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            tradeServiceImplUnderTest.findTradeById(1);
        });
        verify(mockTradeRepository, never()).save(any(Trade.class));
    }

    @Test
    public void findTradeById_whenIdExist() {
        // ARRANGE
        Trade tradeToFind = new Trade("Trade Account", "Type", 1000d);
        tradeToFind.setTradeId(1);
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
        tradeToFind.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeToFind.setDealName("DealName");
        tradeToFind.setDealType("DealType");
        tradeToFind.setSourceListId("SourceListId");
        tradeToFind.setSide("Side");
        doReturn(Optional.of(tradeToFind)).when(mockTradeRepository).findById(tradeToFind.getTradeId());

        // ACT
        Trade tradeFound = tradeServiceImplUnderTest.findTradeById(tradeToFind.getTradeId());

        // ASSERT
        verify(mockTradeRepository, times(1)).findById(tradeToFind.getTradeId());
        assertEquals(tradeToFind, tradeFound);
    }

    @Test
    public void findTradeById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockTradeRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            tradeServiceImplUnderTest.findTradeById(1);
        });
        verify(mockTradeRepository, times(1)).findById(1);
    }

    @Test
    public void findAllTrades() {
        // ARRANGE
        Trade tradeToFind1 = new Trade("Trade Account", "Type", 1000d);
        tradeToFind1.setTradeId(1);
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
        tradeToFind1.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeToFind1.setDealName("DealName");
        tradeToFind1.setDealType("DealType");
        tradeToFind1.setSourceListId("SourceListId");
        tradeToFind1.setSide("Side");

        Trade tradeToFind2 = new Trade("Trade Account", "Type", 1000d);
        tradeToFind2.setTradeId(2);
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
        tradeToFind2.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeToFind2.setDealName("DealName");
        tradeToFind2.setDealType("DealType");
        tradeToFind2.setSourceListId("SourceListId");
        tradeToFind2.setSide("Side");

        Trade tradeToFind3 = new Trade("Trade Account", "Type", 1000d);
        tradeToFind3.setTradeId(3);
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
        tradeToFind3.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeToFind3.setDealName("DealName");
        tradeToFind3.setDealType("DealType");
        tradeToFind3.setSourceListId("SourceListId");
        tradeToFind3.setSide("Side");

        List<Trade> listTradesToFind = new ArrayList<>();
        listTradesToFind.add(tradeToFind1);
        listTradesToFind.add(tradeToFind2);
        listTradesToFind.add(tradeToFind3);

        doReturn(listTradesToFind).when(mockTradeRepository).findAll();

        // ACT
        List<Trade> listTradesFound = tradeServiceImplUnderTest.findAllTrades();

        // ASSERT
        verify(mockTradeRepository, times(1)).findAll();
        assertEquals(listTradesToFind, listTradesFound);
    }

    @Test
    public void deleteTradeById_whenIdExist() {
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
        tradeToDelete.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeToDelete.setDealName("DealName");
        tradeToDelete.setDealType("DealType");
        tradeToDelete.setSourceListId("SourceListId");
        tradeToDelete.setSide("Side");
        tradeToDelete.setTradeId(1);
        doReturn(Optional.of(tradeToDelete)).when(mockTradeRepository).findById(tradeToDelete.getTradeId());

        // ACT
        tradeServiceImplUnderTest.deleteTradeById(tradeToDelete.getTradeId());

        // ASSERT
        verify(mockTradeRepository, times(1)).deleteById(tradeToDelete.getTradeId());
    }

    @Test
    public void deleteTradeById_whenIdNotExist() {
        // ARRANGE
        doReturn(Optional.empty()).when(mockTradeRepository).findById(1);

        // ACT & ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            tradeServiceImplUnderTest.deleteTradeById(1);
        });
        verify(mockTradeRepository, times(1)).findById(1);
        verify(mockTradeRepository, never()).deleteById(1);
    }
}
