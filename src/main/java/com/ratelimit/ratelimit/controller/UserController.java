package com.ratelimit.ratelimit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.ratelimit.ratelimit.services.UserService;


@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users/{userId}")
	public ResponseEntity<String> callAPI(@PathVariable String userId)
	{
		try {
			return this.userService.callAPI(Long.parseLong(userId));
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
