package com.example.pagerdutyagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/1")
public class PagerDutyAgencyController {

	@GetMapping(value = "test")
	public String test() {
		return "hello";
	}

}
