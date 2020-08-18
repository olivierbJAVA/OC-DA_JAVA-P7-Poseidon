package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.services.IBidListService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
 * Class including unit tests for the BidListController Class.
 */
@WebMvcTest(value = BidListController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class BidListControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(BidListControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBidListService mockBidListService;

    // @RequestMapping(value = "/bidList/list")
    @Test
    public void home() {
        //ARRANGE
        BidList bidListTest1 = new BidList();
        bidListTest1.setBidListId(1);
        bidListTest1.setAccount("Account Test");
        bidListTest1.setType("Type Test");
        bidListTest1.setBidQuantity(10d);
        bidListTest1.setAskQuantity(30d);
        bidListTest1.setBid(123.45d);
        bidListTest1.setAsk(321.54d);
        bidListTest1.setBenchmark("Benchmark Test");
        bidListTest1.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest1.setCommentary("Commentary Test");
        bidListTest1.setSecurity("Security Test");
        bidListTest1.setStatus("StatusTest");
        bidListTest1.setTrader("Trader Test");
        bidListTest1.setBook("Book Test");
        bidListTest1.setCreationName("CreationName Test");
        bidListTest1.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest1.setRevisionName("RevisionName Test");
        bidListTest1.setRevisionDate(valueOf("2020-08-10 09:10:23.0"));
        bidListTest1.setDealName("DealName Test");
        bidListTest1.setDealType("DealType Test");
        bidListTest1.setSourceListId("SourceListId Test");
        bidListTest1.setSide("Side Test");

        BidList bidListTest2 = new BidList();
        bidListTest2.setBidListId(2);
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

        BidList bidListTest3 = new BidList();
        bidListTest3.setBidListId(3);
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

        List<BidList> allBidListsToFind = new ArrayList<>();
        allBidListsToFind.add(bidListTest1);
        allBidListsToFind.add(bidListTest2);
        allBidListsToFind.add(bidListTest3);

        doReturn(allBidListsToFind).when(mockBidListService).findAllBidLists();

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/bidList/list"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("bidLists", allBidListsToFind))
                    .andExpect(view().name("bidList/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, times(1)).findAllBidLists();
    }

    // @GetMapping(value = "/bidList/add"")
    @Test
    public void addBidForm() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/bidList/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("bidList/add"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    // @PostMapping(value = "/bidList/validate"")
    @Test
    public void validate_whenNoError() {
        //ARRANGE
        BidList bidListTest = new BidList();
        bidListTest.setBidListId(1);
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
        bidListTest.setDealName("DealName Test");
        bidListTest.setDealType("DealType Test");
        bidListTest.setSourceListId("SourceListId Test");
        bidListTest.setSide("Side Test");

        doReturn(bidListTest).when(mockBidListService).createBidList(bidListTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/bidList/validate")
                    .param("account", "Account Test")
                    .param("type", "Type Test")
                    .param("bidQuantity", "10"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/bidList/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, times(1)).createBidList(any(BidList.class));
    }

    // @PostMapping(value = "/bidList/validate"")
    @Test
    public void validate_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            // Error in account (mandatory field)
            mockMvc.perform(post("/bidList/update/1")
                    .param("account", "")
                    .param("type", "Type Test")
                    .param("bidQuantity", "10"))
                    .andExpect(model().attributeHasFieldErrors("bidList", "account"))
                    .andExpect(view().name("bidList/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, never()).createBidList(any(BidList.class));
    }

    // @GetMapping(value = "/bidList/update/{id}"")
    @Test
    public void showUpdateForm() {
        //ARRANGE
        BidList bidListTest = new BidList();
        bidListTest.setBidListId(1);
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
        bidListTest.setDealName("DealName Test");
        bidListTest.setDealType("DealType Test");
        bidListTest.setSourceListId("SourceListId Test");
        bidListTest.setSide("Side Test");

        doReturn(bidListTest).when(mockBidListService).findBidListById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/bidList/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("bidList", bidListTest))
                    .andExpect(view().name("bidList/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, times(1)).findBidListById(1);
    }

    // @PostMapping(value = "/bidList/update/{id}"")
    @Test
    public void updateBid_whenNoError() {
        //ARRANGE
        BidList bidListTest = new BidList();
        bidListTest.setBidListId(1);
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
        bidListTest.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest.setDealName("DealName Test");
        bidListTest.setDealType("DealType Test");
        bidListTest.setSourceListId("SourceListId Test");
        bidListTest.setSide("Side Test");

        doReturn(bidListTest).when(mockBidListService).findBidListById(bidListTest.getBidListId());
        doReturn(bidListTest).when(mockBidListService).updateBidList(bidListTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/bidList/update/1")
                    .param("bidListId", "1")
                    .param("account", "Account Test")
                    .param("type", "Type Test")
                    .param("bidQuantity", "10"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/bidList/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, times(1)).updateBidList(any(BidList.class));
    }

    // @PostMapping(value = "/bidList/update/{id}"")
    @Test
    public void updateBid_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            // Error in account (mandatory field)
            mockMvc.perform(post("/bidList/update/1")
                    .param("bidListId", "1")
                    .param("account", "")
                    .param("type", "Type Test")
                    .param("bidQuantity", "10"))
                    .andExpect(model().attributeHasFieldErrors("bidList", "account"))
                    .andExpect(view().name("bidList/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, never()).updateBidList(any(BidList.class));
    }

    // @GetMapping(value = "/bidList/delete/{id}"")
    @Test
    public void deleteBid_whenBidExist() {
        //ARRANGE
        doNothing().when(mockBidListService).deleteBidListById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/bidList/delete/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/bidList/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, times(1)).deleteBidListById(1);
    }

    // @GetMapping(value = "/bidList/delete/{id}"")
    @Test
    public void deleteBid_whenBidNotExist() {
        //ARRANGE
        doThrow(RecordNotFoundException.class).when(mockBidListService).deleteBidListById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/bidList/delete/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("errorRecordNotFound"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, times(1)).deleteBidListById(1);
    }
}