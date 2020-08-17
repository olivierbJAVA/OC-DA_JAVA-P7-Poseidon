package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.IBidListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;


@Controller
public class BidListController {

    private static final Logger logger = LoggerFactory.getLogger(BidListController.class);

    @Autowired
    IBidListService bidListService;

    @RolesAllowed({"USER","ADMIN"})
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        logger.info("Request : GET /bidList/list");

        List<BidList> bidLists = bidListService.findAllBidLists();
        model.addAttribute("bidLists", bidLists);

        logger.info("Success : bidLists found, returning 'bidList/list' view");

        return "bidList/list";
    }

    @RolesAllowed({"USER","ADMIN"})
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidList) {

        logger.info("Request : GET /bidList/add");
        logger.info("Success : returning 'bidList/add' view");

        return "bidList/add";
    }

    @RolesAllowed({"USER","ADMIN"})
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {

        logger.info("Request : POST /bidList/validate");

        if (!result.hasErrors()) {
            bidList.setCreationDate(valueOf(now()));
            bidList.setBidListDate(valueOf(now()));
            bidListService.createBidList(bidList);

            logger.info("Success : new bidList created, redirect to '/bidList/list' view");

            return "redirect:/bidList/list";
        }

        logger.error("Error in fields validation : new bidList not created, returning '/bidList/add' view");

        return "bidList/add";
    }

    @RolesAllowed({"USER","ADMIN"})
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /bidList/update/{}", id);

        BidList bidList = bidListService.findBidListById(id);
        model.addAttribute("bidList", bidList);

        logger.info("Success : bidList with id {} to update found, returning '/bidList/update' view", id);

        return "bidList/update";
    }

    @RolesAllowed({"USER","ADMIN"})
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@Valid BidList bidList, BindingResult result, Model model) {

        logger.info("Request : POST /bidList/update/{}", bidList.getBidListId());

        if (result.hasErrors()) {

            logger.error("Error in fields : bidList with id {} not updated, returning '/bidList/update' view", bidList.getBidListId());

            return "bidList/update";
        }

        bidList.setCreationDate(bidListService.findBidListById(bidList.getBidListId()).getCreationDate());
        bidList.setRevisionDate(valueOf(now()));
        bidList.setBidListDate(valueOf(now()));
        bidListService.updateBidList(bidList);

        logger.info("Success : bidList with id {} updated, redirect to '/bidList/list'", bidList.getBidListId());

        return "redirect:/bidList/list";
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /bidList/delete/{}", id);

        bidListService.deleteBidListById(id);

        logger.info("Success : bidList with id {} deleted, redirect to '/bidList/list'", id);

        return "redirect:/bidList/list";
    }
}
