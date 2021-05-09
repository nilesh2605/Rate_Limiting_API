package com.ratelimit.ratelimit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.ratelimit.ratelimit.services.UserService;


@RestController
public class MyController {
	@Autowired
	private UserService userService;

	@GetMapping("/users/{userId}")
	public String getUser(@PathVariable String userId)
	{
		return this.userService.getUser(Long.parseLong(userId));
	}
	//name to be changed
}
