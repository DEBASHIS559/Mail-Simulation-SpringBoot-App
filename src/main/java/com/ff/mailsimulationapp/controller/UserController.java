package com.ff.mailsimulationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.service.UserService;

@RestController
@RequestMapping("/app")
public class UserController {
	@Autowired
	private UserService userService;
	
//	@PostMapping
//	public ResponseEntity<ResponseStructure<String>> sendMail(){
//		
//	}
}
