package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller in charge of managing the endpoints for Home pages.
 */
@Controller
public class HomeController
{
	/**
	 * Method managing the "/" endpoint HTTP request.
	 *
	 * @param model The Model
	 * @return The name of the View
	 */
	@RequestMapping("/")
	public String home(Model model)
	{
		return "redirect:/bidList/list";
	}

	/**
	 * Method managing the "/admin/home" endpoint HTTP request.
	 *
	 * @param model The Model
	 * @return The name of the View
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/user/list";
	}
}
