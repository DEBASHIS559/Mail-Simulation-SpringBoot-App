package com.ff.mailsimulationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.service.PasswordService;

@RestController
@RequestMapping("/password")
public class PasswordController {
	
	
	@Autowired
	private PasswordService passwordService;
	
	@PostMapping("/forgot")
	public ResponseEntity<ResponseStructure<String>> forgotPassword(@RequestParam String email){
		
		return passwordService.forgotPassword(email);
		
	}
	
	
	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<String>> verifyToken(@RequestParam String token, @RequestParam String answer){
		
		return passwordService.verifySecurityQuestion(token, answer);
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<ResponseStructure<String>> updatePassword(@RequestHeader String newPassword, @RequestHeader String token){
		return passwordService.updatePassword(token, newPassword);
	}
	

}
