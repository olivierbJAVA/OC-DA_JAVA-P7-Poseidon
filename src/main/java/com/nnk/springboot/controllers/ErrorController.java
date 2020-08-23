package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller in charge of managing the endpoints for error pages.
 */
@Controller
public class ErrorController {

    /**
     * Method managing the "/errorAccessDenied" when access is not authorized.
     *
     * @return A ModelAndView object including information and the name of the View
     */
    @GetMapping("/errorAccessDenied")
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("errorAccessDenied");
        return mav;
    }
}
