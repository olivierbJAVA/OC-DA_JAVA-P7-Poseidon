package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.services.ITradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Timestamp.valueOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class including unit tests for the TradeController Class.
 */
@WebMvcTest(value = TradeController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class TradeControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(TradeControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITradeService mockTradeService;

    @BeforeEach
    private void setUpPerTest() {
    }

    // @RequestMapping(value = "/trade/list")
    @Test
    public void home() {
        //ARRANGE
        Trade tradeTest1 = new Trade();
        tradeTest1.setAccount("Trade Account");
        tradeTest1.setType("Type");
        tradeTest1.setBuyQuantity(1000d);
        tradeTest1.setSellQuantity(100d);
        tradeTest1.setBuyPrice(123.00d);
        tradeTest1.setSellPrice(456.12d);
        tradeTest1.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeTest1.setSecurity("Security");
        tradeTest1.setStatus("Status");
        tradeTest1.setTrader("Trader");
        tradeTest1.setBenchmark("Benchmark");
        tradeTest1.setBook("Book");
        tradeTest1.setCreationName("CreationName");
        tradeTest1.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeTest1.setRevisionName("RevisionName");
        tradeTest1.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeTest1.setDealName("DealName");
        tradeTest1.setDealType("DealType");
        tradeTest1.setSourceListId("SourceListId");
        tradeTest1.setSide("Side");

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

        List<Trade> allTradesToFind = new ArrayList<>();
        allTradesToFind.add(tradeTest1);
        allTradesToFind.add(tradeTest1);
        allTradesToFind.add(tradeTest1);

        doReturn(allTradesToFind).when(mockTradeService).findAllTrades();

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/trade/list"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("trades", allTradesToFind))
                    .andExpect(view().name("trade/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockTradeService, times(1)).findAllTrades();
    }

    // @GetMapping(value = "/trade/add"")
    @Test
    public void addTradeForm() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/trade/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("trade/add"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    // @PostMapping(value = "/trade/validate"")
    @Test
    public void validate_whenNoError() {
        //ARRANGE
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

        doReturn(tradeTest).when(mockTradeService).createTrade(tradeTest);

        List<Trade> listTrades = new ArrayList<>();
        listTrades.add(tradeTest);

        doReturn(listTrades).when(mockTradeService).findAllTrades();

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/trade/validate")
                    .param("account", "Trade Account")
                    .param("type", "Type")
                    .param("buyQuantity", "1000"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/trade/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockTradeService, times(1)).createTrade(any(Trade.class));
        verify(mockTradeService, times(1)).findAllTrades();
    }

    // @PostMapping(value = "/trade/validate"")
    @Test
    public void validate_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/trade/update/1")
                    .param("account", "")
                    .param("type", "Type")
                    .param("buyQuantity", "1000"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("trade/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockTradeService, never()).createTrade(any(Trade.class));
        verify(mockTradeService, never()).findAllTrades();
    }

    // @GetMapping(value = "/trade/update/{id}"")
    @Test
    public void showUpdateForm() {
        //ARRANGE
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

        doReturn(tradeTest).when(mockTradeService).findTradeById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/trade/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("trade", tradeTest))
                    .andExpect(view().name("trade/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockTradeService, times(1)).findTradeById(1);
    }

    // @PostMapping(value = "/trade/update/{id}"")
    @Test
    public void updateTrade_whenNoError() {
        //ARRANGE
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

        doReturn(tradeTest).when(mockTradeService).updateTrade(tradeTest);

        List<Trade> listTrades = new ArrayList<>();
        listTrades.add(tradeTest);
        doReturn(listTrades).when(mockTradeService).findAllTrades();

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/trade/update/1")
                    .param("account", "Trade Account")
                    .param("type", "Type")
                    .param("buyQuantity", "1000"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/trade/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockTradeService, times(1)).updateTrade(any(Trade.class));
        verify(mockTradeService, times(1)).findAllTrades();
    }

    // @PostMapping(value = "/trade/update/{id}"")
    @Test
    public void updateTrade_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/trade/update/1")
                    .param("account", "")
                    .param("type", "Type")
                    .param("buyQuantity", "1000"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("trade/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockTradeService, never()).updateTrade(any(Trade.class));
        verify(mockTradeService, never()).findAllTrades();
    }

    // @GetMapping(value = "/trade/delete/{id}"")
    @Test
    public void deleteTrade_whenTradeExist() {
        //ARRANGE
        Trade tradeTest1 = new Trade();
        tradeTest1.setAccount("Trade Account");
        tradeTest1.setType("Type");
        tradeTest1.setBuyQuantity(1000d);
        tradeTest1.setSellQuantity(100d);
        tradeTest1.setBuyPrice(123.00d);
        tradeTest1.setSellPrice(456.12d);
        tradeTest1.setTradeDate(valueOf("2020-08-10 10:20:30.0"));
        tradeTest1.setSecurity("Security");
        tradeTest1.setStatus("Status");
        tradeTest1.setTrader("Trader");
        tradeTest1.setBenchmark("Benchmark");
        tradeTest1.setBook("Book");
        tradeTest1.setCreationName("CreationName");
        tradeTest1.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        tradeTest1.setRevisionName("RevisionName");
        tradeTest1.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        tradeTest1.setDealName("DealName");
        tradeTest1.setDealType("DealType");
        tradeTest1.setSourceListId("SourceListId");
        tradeTest1.setSide("Side");

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

        List<Trade> allTradesToFind = new ArrayList<>();
        allTradesToFind.add(tradeTest1);
        allTradesToFind.add(tradeTest1);
        allTradesToFind.add(tradeTest1);

        doReturn(allTradesToFind).when(mockTradeService).findAllTrades();

        doNothing().when(mockTradeService).deleteTradeById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/trade/delete/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/trade/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockTradeService, times(1)).deleteTradeById(1);
        verify(mockTradeService, times(1)).findAllTrades();
    }

    // @GetMapping(value = "/trade/delete/{id}"")
    @Test
    public void deleteTrade_whenTradeNotExist() {
        //ARRANGE
        doThrow(RecordNotFoundException.class).when(mockTradeService).deleteTradeById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/trade/delete/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("errorRecordNotFound"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockTradeService, times(1)).deleteTradeById(1);
        verify(mockTradeService, never()).findAllTrades();
    }

}