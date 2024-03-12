package com.ff.mailsimulationapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<User>> saveUser(@Valid @RequestBody User user) {
		
		return userService.createUser(user);
	}
	
	@PostMapping("/login")
	ResponseEntity<ResponseStructure<User>> login(@Valid @RequestParam String email, @Valid @RequestParam String password) {
		return userService.login(email, password);
	}
	


}
