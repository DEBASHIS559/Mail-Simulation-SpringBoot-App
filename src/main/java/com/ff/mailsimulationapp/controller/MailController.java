package com.ff.mailsimulationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.mailsimulationapp.dto.InboxResponse;
import com.ff.mailsimulationapp.dto.MailDto;
import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.dto.SentItemsResponse;
import com.ff.mailsimulationapp.entity.Mail;
import com.ff.mailsimulationapp.service.MailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/app")
public class MailController {

	@Autowired
	private MailService mailService;

	@Operation(description = "To send email", summary = "mail will be saved in database")
	@ApiResponses(value = { @ApiResponse(description = "mail sent successfully", responseCode = "201"),
			@ApiResponse(description = "unauthorized", responseCode = "401", content = @Content) })
	@PostMapping("/send-mail")
	public ResponseEntity<ResponseStructure<String>> sendMail(@Valid @RequestBody MailDto mailDto) {
		return mailService.sendMail(mailDto);
	}

	@Operation(description = "To view inbox", summary = "inbox will be fetched")
	@ApiResponses(value = { @ApiResponse(description = "mail fetched successfully", responseCode = "200"),
			@ApiResponse(description = "unauthorized", responseCode = "401", content = @Content) })
	@GetMapping("/inbox/{email}")
	public ResponseEntity<ResponseStructure<List<InboxResponse>>> viewInbox(@PathVariable String email) {
		return mailService.viewInbox(email);
	}


	@Operation(description = "To view sent items", summary = "sent items will be fetched")
	@ApiResponses(value = { @ApiResponse(description = "sent items fetched successfully", responseCode = "200"),
			@ApiResponse(description = "unauthorized", responseCode = "401", content = @Content) })
	@GetMapping("/sent/{email}")
	public ResponseEntity<ResponseStructure<List<SentItemsResponse>>> viewSentItem(@PathVariable String email) {
		return mailService.viewSentItem(email);
	}

	// this method is for showing draft mails of perticular user

	@Operation(description = "To view drafts", summary = "drafts will be fetched")
	@ApiResponses(value = { @ApiResponse(description = "drafts fetched successfully", responseCode = "200"),
			@ApiResponse(description = "unauthorized", responseCode = "401", content = @Content) })
	@GetMapping("/view-draft/{email}")
	public ResponseEntity<ResponseStructure<List<InboxResponse>>> viewDraftItem(@PathVariable String email) {
		return mailService.viewDraftItem(email);
	}


	@Operation(description = "To update draft mail", summary = "drafts will be edited and sent to the user")
	@ApiResponses(value = { @ApiResponse(description = "drafts edited successfully", responseCode = "200"),
			@ApiResponse(description = "unauthorized", responseCode = "401", content = @Content) })
	@PutMapping("/send-draft/{email}/{mailid}")
	public ResponseEntity<ResponseStructure<String>> editDraftMail(@PathVariable String email, @PathVariable int mailid,
			@RequestBody MailDto mailDto) {
		return mailService.editDraftMail(email, mailid, mailDto);
	}

	@Operation(description = "To delete an email", summary = "mail will be deleted")
	@ApiResponses(value = { @ApiResponse(description = "mail deleted successfully", responseCode = "200"),
			@ApiResponse(description = "unauthorized", responseCode = "401", content = @Content) })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<List<Mail>>> deletemails(@Valid @RequestBody List<Integer> mailids) {

		return mailService.deleteMailsbyid(mailids);
	}

}
