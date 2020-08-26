package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller in charge of managing the endpoints for Home pages.
 */
@Controller
public class HomeController
{
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Method managing the GET "/" endpoint HTTP request.
	 *
	 * @return The name of the View
	 */
	@GetMapping("/")
	public String home()
	{
		logger.info("Request : GET /");
		logger.info("Success : redirect to '/bidList/list' view");

		return "redirect:/bidList/list";
	}

	/**
	 * Method managing the GET "/admin/home" endpoint HTTP request.
	 *
	 * @return The name of the View
	 */
	@GetMapping("/admin/home")
	public String adminHome()
	{
		logger.info("Request : GET /admin/home");
		logger.info("Success : redirect to '/user/list' view");

		return "redirect:/user/list";
	}
}
