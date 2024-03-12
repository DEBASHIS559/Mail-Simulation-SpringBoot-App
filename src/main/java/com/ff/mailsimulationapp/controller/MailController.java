package com.ff.mailsimulationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.mailsimulationapp.dto.MailDto;
import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.entity.Mail;
import com.ff.mailsimulationapp.service.MailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	//this method is for sending mail
	@PostMapping("/send-mail")
	public ResponseEntity<ResponseStructure<String>> sendMail(@Valid @RequestBody MailDto mailDto){
		return mailService.sendMail(mailDto);
	}
	
	//this method is for showing inbox of perticular user
	@GetMapping("/inbox/{email}")
	public ResponseEntity<ResponseStructure<List<Mail>>> viewInbox(@PathVariable String email){
		return mailService.viewInbox(email);
	}
}
