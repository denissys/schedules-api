package com.denissys.schedules.api.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/")
public class SwaggerController {

	@RequestMapping(method = GET)
	public ModelAndView doc() {
		return new ModelAndView("redirect:swagger-ui.html");
	}

}
