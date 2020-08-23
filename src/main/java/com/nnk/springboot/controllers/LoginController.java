package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller in charge of managing the endpoints for error403 page.
 */
@Controller
public class LoginController {

    /**
     * Method managing the "/error403" access denied.
     *
     * @return A ModelAndView object including information and the name of the View
     */
    @GetMapping("/error403")
    public ModelAndView notAuthorized() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("error403");
        return mav;
    }
}
