package com.simplilearn.webservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContoller {

	@GetMapping("/")
	public String serverUp() {
		return "Server is up and running!";
	}
}
