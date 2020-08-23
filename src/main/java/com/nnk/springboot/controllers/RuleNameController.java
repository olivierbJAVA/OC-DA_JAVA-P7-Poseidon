package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.IRuleNameService;
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

import javax.validation.Valid;
import java.util.List;

/**
 * Controller in charge of managing the endpoints for RuleName entities.
 */
@Controller
public class RuleNameController {

    private static final Logger logger = LoggerFactory.getLogger(RuleNameController.class);

    @Autowired
    IRuleNameService ruleNameService;

    /**
     * Method managing the "/ruleName/list" endpoint HTTP request to get the list of all RuleNames.
     *
     * @param model The Model
     * @return The name of the View
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        logger.info("Request : /ruleName/list");

        List<RuleName> ruleNames = ruleNameService.findAllRuleNames();
        model.addAttribute("ruleNames", ruleNames);

        logger.info("Success : ruleNames found, returning 'ruleName/list' view");

        return "ruleName/list";
    }

    /**
     * Method managing the GET "/ruleName/add" endpoint HTTP request to add a RuleName.
     *
     * @param ruleName The RuleName
     * @return The name of the View
     */
    @GetMapping("/ruleName/add")
    public String addRuleNameForm(RuleName ruleName) {

        logger.info("Request : GET /ruleName/add");
        logger.info("Success : returning 'ruleName/add' view");

        return "ruleName/add";
    }

    /**
     * Method managing the POST "/ruleName/validate" endpoint HTTP request to add a RuleName.
     *
     * @param ruleName The RuleName to add
     * @param result The BindingResult containing the result of the fields validation
     * @param model The Model
     * @return The name of the View
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        logger.info("Request : POST /ruleName/validate");

        if (!result.hasErrors()) {
            ruleNameService.createRuleName(ruleName);

            logger.info("Success : new ruleName created, redirect to '/ruleName/list' view");

            return "redirect:/ruleName/list";
        }

        logger.error("Error in fields validation : new ruleName not created, returning '/ruleName/add' view");

        return "ruleName/add";
    }

    /**
     * Method managing the GET "/ruleName/update/{id}" endpoint HTTP request to update a RuleName.
     *
     * @param id The id of the RuleName to update
     * @param model The Model
     * @return The name of the View
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /ruleName/update/{}", id);

        RuleName ruleName = ruleNameService.findRuleNameById(id);
        model.addAttribute("ruleName", ruleName);

        logger.info("Success : ruleName with id {} to update found, returning '/ruleName/update' view", id);

        return "ruleName/update";
    }

    /**
     * Method managing the POST "/ruleName/update/{id}" endpoint HTTP request to update a RuleName.
     *
     * @param ruleName The RuleName to update
     * @param result The BindingResult containing the result of the fields validation
     * @param model The Model
     * @return The name of the View
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@Valid RuleName ruleName, BindingResult result, Model model) {

        logger.info("Request : POST /rating/update/{}", ruleName.getId());

        if (result.hasErrors()) {

            logger.error("Error in fields : ruleName with id {} not updated, returning '/ruleName/update' view", ruleName.getId());

            return "ruleName/update";
        }

        ruleNameService.updateRuleName(ruleName);

        logger.info("Success : ruleName with id {} updated, redirect to '/ruleName/list'", ruleName.getId());

        return "redirect:/ruleName/list";
    }

    /**
     * Method managing the GET "/ruleName/delete/{id}" endpoint HTTP request to delete a RuleName.
     *
     * @param id The id of the RuleName to delete
     * @param model The Model
     * @return The name of the View
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /ruleName/delete/{}", id);

        ruleNameService.deleteRuleNameById(id);

        logger.info("Success : ruleName with id {} deleted, redirect to '/ruleName/list'", id);

        return "redirect:/ruleName/list";
    }
}
