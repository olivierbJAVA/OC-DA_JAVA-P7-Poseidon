package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.services.IBidListService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
 * Class including unit tests for the BidListController Class (security is disabled and has dedicated tests).
 */
@WebMvcTest(value = BidListController.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class BidListControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(BidListControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBidListService mockBidListService;

    // @GetMapping(value = "/bidList/list")
    @Test
    public void home() {
        //ARRANGE
        BidList bidListTest1 = new BidList();
        bidListTest1.setBidListId(1);
        bidListTest1.setAccount("Account");
        bidListTest1.setType("Type");
        bidListTest1.setBidQuantity(10d);
        bidListTest1.setAskQuantity(30d);
        bidListTest1.setBid(123.45d);
        bidListTest1.setAsk(321.54d);
        bidListTest1.setBenchmark("Benchmark");
        bidListTest1.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest1.setCommentary("Commentary");
        bidListTest1.setSecurity("Security");
        bidListTest1.setStatus("StatusTest");
        bidListTest1.setTrader("Trader");
        bidListTest1.setBook("Book");
        bidListTest1.setCreationName("CreationName");
        bidListTest1.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest1.setRevisionName("RevisionName");
        bidListTest1.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest1.setDealName("DealName");
        bidListTest1.setDealType("DealType");
        bidListTest1.setSourceListId("SourceListId");
        bidListTest1.setSide("Side");

        BidList bidListTest2 = new BidList();
        bidListTest2.setBidListId(2);
        bidListTest2.setAccount("Account");
        bidListTest2.setType("Type");
        bidListTest2.setBidQuantity(10d);
        bidListTest2.setAskQuantity(30d);
        bidListTest2.setBid(123.45d);
        bidListTest2.setAsk(321.54d);
        bidListTest2.setBenchmark("Benchmark");
        bidListTest2.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest2.setCommentary("Commentary");
        bidListTest2.setSecurity("Security");
        bidListTest2.setStatus("StatusTest");
        bidListTest2.setTrader("Trader");
        bidListTest2.setBook("Book");
        bidListTest2.setCreationName("CreationName");
        bidListTest2.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest2.setRevisionName("RevisionName");
        bidListTest2.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest2.setDealName("DealName");
        bidListTest2.setDealType("DealType");
        bidListTest2.setSourceListId("SourceListId");
        bidListTest2.setSide("Side");

        BidList bidListTest3 = new BidList();
        bidListTest3.setBidListId(3);
        bidListTest3.setAccount("Account");
        bidListTest3.setType("Type");
        bidListTest3.setBidQuantity(10d);
        bidListTest3.setAskQuantity(30d);
        bidListTest3.setBid(123.45d);
        bidListTest3.setAsk(321.54d);
        bidListTest3.setBenchmark("Benchmark");
        bidListTest3.setBidListDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest3.setCommentary("Commentary");
        bidListTest3.setSecurity("Security");
        bidListTest3.setStatus("StatusTest");
        bidListTest3.setTrader("Trader");
        bidListTest3.setBook("Book");
        bidListTest3.setCreationName("CreationName");
        bidListTest3.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest3.setRevisionName("RevisionName");
        bidListTest3.setRevisionDate(valueOf("2020-08-10 10:20:30.0"));
        bidListTest3.setDealName("DealName");
        bidListTest3.setDealType("DealType");
        bidListTest3.setSourceListId("SourceListId");
        bidListTest3.setSide("Side");

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
    public void addBidListForm() {
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
        bidListTest.setAccount("Account");
        bidListTest.setType("Type");
        bidListTest.setBidQuantity(10d);
        bidListTest.setAskQuantity(30d);
        bidListTest.setBid(123.45d);
        bidListTest.setAsk(321.54d);
        bidListTest.setBenchmark("Benchmark");
        bidListTest.setCommentary("Commentary");
        bidListTest.setSecurity("Security");
        bidListTest.setStatus("StatusTest");
        bidListTest.setTrader("Trader");
        bidListTest.setBook("Book");
        bidListTest.setCreationName("CreationName");
        bidListTest.setDealName("DealName");
        bidListTest.setDealType("DealType");
        bidListTest.setSourceListId("SourceListId");
        bidListTest.setSide("Side");

        doReturn(bidListTest).when(mockBidListService).createBidList(bidListTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/bidList/validate")
                    .param("account", "Account")
                    .param("type", "Type")
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
            mockMvc.perform(post("/bidList/validate")
                    .param("account", "")
                    .param("type", "Type")
                    .param("bidQuantity", "10"))
                    .andExpect(model().attributeHasFieldErrors("bidList", "account"))
                    .andExpect(view().name("bidList/add"));
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
        bidListTest.setAccount("Account");
        bidListTest.setType("Type");
        bidListTest.setBidQuantity(10d);
        bidListTest.setAskQuantity(30d);
        bidListTest.setBid(123.45d);
        bidListTest.setAsk(321.54d);
        bidListTest.setBenchmark("Benchmark");
        bidListTest.setBidListDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest.setCommentary("Commentary");
        bidListTest.setSecurity("Security");
        bidListTest.setStatus("StatusTest");
        bidListTest.setTrader("Trader");
        bidListTest.setBook("Book");
        bidListTest.setCreationName("CreationName");
        bidListTest.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest.setDealName("DealName");
        bidListTest.setDealType("DealType");
        bidListTest.setSourceListId("SourceListId");
        bidListTest.setSide("Side");

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
    public void updateBidList_whenNoError() {
        //ARRANGE
        BidList bidListTest = new BidList();
        bidListTest.setBidListId(1);
        bidListTest.setAccount("Account");
        bidListTest.setType("Type");
        bidListTest.setBidQuantity(10d);
        bidListTest.setAskQuantity(30d);
        bidListTest.setBid(123.45d);
        bidListTest.setAsk(321.54d);
        bidListTest.setBenchmark("Benchmark");
        bidListTest.setCommentary("Commentary");
        bidListTest.setSecurity("Security");
        bidListTest.setStatus("StatusTest");
        bidListTest.setTrader("Trader");
        bidListTest.setBook("Book");
        bidListTest.setCreationName("CreationName");
        bidListTest.setCreationDate(valueOf("2020-07-23 10:20:30.0"));
        bidListTest.setRevisionName("RevisionName");
        bidListTest.setDealName("DealName");
        bidListTest.setDealType("DealType");
        bidListTest.setSourceListId("SourceListId");
        bidListTest.setSide("Side");

        doReturn(bidListTest).when(mockBidListService).findBidListById(bidListTest.getBidListId());
        doReturn(bidListTest).when(mockBidListService).updateBidList(bidListTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/bidList/update/1")
                    .param("bidListId", "1")
                    .param("account", "Account")
                    .param("type", "Type")
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
    public void updateBidList_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            // Error in account (mandatory field)
            mockMvc.perform(post("/bidList/update/1")
                    .param("bidListId", "1")
                    .param("account", "")
                    .param("type", "Type")
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
    public void deleteBidList_whenBidListExist() {
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
    public void deleteBidList_whenBidListNotExist() {
        //ARRANGE
        doThrow(ResourceNotFoundException.class).when(mockBidListService).deleteBidListById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/bidList/delete/1"))
                    .andExpect(status().isNotFound())
                    .andExpect(view().name("errorResourceNotFound"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockBidListService, times(1)).deleteBidListById(1);
    }
}