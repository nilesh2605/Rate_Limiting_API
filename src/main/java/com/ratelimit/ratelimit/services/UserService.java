package com.ratelimit.ratelimit.services;

import org.springframework.http.ResponseEntity;

public interface UserService {
	public ResponseEntity<String> callAPI(long userId);
}
