package com.ff.mailsimulationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.mailsimulationapp.dto.InboxResponse;
import com.ff.mailsimulationapp.dto.MailDto;
import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.entity.Mail;
import com.ff.mailsimulationapp.service.MailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/app")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@Operation(description = "To send email" , summary = "mail will be saved in database")
	@ApiResponses(value = {@ApiResponse(description = "mail sent successfully",responseCode ="201"),@ApiResponse(description = "mail not sent",responseCode ="403")})
	@PostMapping("/send-mail")
	public ResponseEntity<ResponseStructure<String>> sendMail(@Valid @RequestBody MailDto mailDto){
		return mailService.sendMail(mailDto);
	}
	
	@Operation(description = "To view email" , summary = "mail will be fetched")
	@ApiResponses(value = {@ApiResponse(description = "mail fetched successfully",responseCode ="202"),@ApiResponse(description = "mail not fetched",responseCode ="403")})
	@GetMapping("/inbox/{email}")
	public ResponseEntity<ResponseStructure<List<InboxResponse>>> viewInbox(@PathVariable String email){
		return mailService.viewInbox(email);
	}
	@Operation(description = "To delete an email" , summary = "mail will be deleted")
	@ApiResponses(value = {@ApiResponse(description = "mail deleted successfully",responseCode ="202"),@ApiResponse(description = "mail not deleted",responseCode ="403")})
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<List<Mail>>> deletemails(@Valid @RequestBody  List<Integer> mailids ) {
		
		
		return mailService.deleteMailsbyid(mailids );
	}
	
	
	
}
