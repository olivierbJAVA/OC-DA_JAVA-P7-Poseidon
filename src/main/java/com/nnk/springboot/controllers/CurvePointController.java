package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.ICurvePointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;

@Controller
public class CurvePointController {

    private static final Logger logger = LoggerFactory.getLogger(CurvePointController.class);

    @Autowired
    ICurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        logger.info("Request : GET /curvePoint/list");

        List<CurvePoint> curvePoints = curvePointService.findAllCurvePoints();
        model.addAttribute("curvePoints", curvePoints );

        logger.info("Success : curvePoints found, returning 'curvePoint/list' view");

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint) {

        logger.info("Request : GET /curvePoint/add");
        logger.info("Success : returning 'curvePoint/add' view");

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        logger.info("Request : POST /curvePoint/validate");

        if (!result.hasErrors()) {
            curvePoint.setCreationDate(valueOf(now()));
            curvePoint.setAsOfDate(valueOf(now()));
            curvePointService.createCurvePoint(curvePoint);

            logger.info("Success : new curvePoint created, redirect to '/curvePoint/list' view");

            return "redirect:/curvePoint/list";
        }

        logger.error("Error in fields validation : new curvePoint not created, returning '/curvePoint/add' view");

        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /curvePoint/update/{}", id);

        CurvePoint curvePoint = curvePointService.findCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);

        logger.info("Success : curvePoint with id {} to update found, returning '/curvePoint/update' view", id);

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        logger.info("Request : POST /curvePoint/update/{}", curvePoint.getId());

        if (result.hasErrors()) {

            logger.error("Error in fields : curvePoint with id {} not updated, returning '/curvePoint/update' view", curvePoint.getId());

            return "curvePoint/update";
        }
        curvePoint.setCreationDate(curvePointService.findCurvePointById(curvePoint.getId()).getCreationDate());
        curvePoint.setAsOfDate(valueOf(now()));
        curvePointService.updateCurvePoint(curvePoint);

        logger.info("Success : curvePoint with id {} updated, redirect to '/rating/list'", curvePoint.getId());

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /curvePoint/delete/{}", id);

        curvePointService.deleteCurvePointById(id);

        logger.info("Success : curvePoint with id {} deleted, redirect to '/curvePoint/list'", id);

        return "redirect:/curvePoint/list";
    }
}
