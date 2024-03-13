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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/password")
public class PasswordController {
	
	
	@Autowired
	private PasswordService passwordService;
	
	

	@Operation(description = "forgot password", summary = "forgot password")
	@ApiResponses(value = { @ApiResponse(description = "verification mail sent successfully", responseCode = "200") })
	@PostMapping("/forgot")
	public ResponseEntity<ResponseStructure<String>> forgotPassword(@RequestParam String email){
		
		return passwordService.forgotPassword(email);
		
	}
	
	@Operation(description = "verify token", summary = "verification of forgot password token and security question")
	@ApiResponses(value = { @ApiResponse(description = "verification done", responseCode = "200") })
	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<String>> verifyToken(@RequestParam String token, @RequestParam String answer){
		
		return passwordService.verifySecurityQuestion(token, answer);
	}
	
	@Operation(description = "update password", summary = "update passsowrd after successful verification")
	@ApiResponses(value = { @ApiResponse(description = "password update kindly login.", responseCode = "200") })
	@PostMapping("/update")
	public ResponseEntity<ResponseStructure<String>> updatePassword(@RequestHeader String newPassword, @RequestHeader String token){
		return passwordService.updatePassword(token, newPassword);
	}
	

}
