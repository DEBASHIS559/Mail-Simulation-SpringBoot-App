package com.ff.mailsimulationapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ff.mailsimulationapp.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(MailFailedToSentException.class)
	public ResponseEntity<ResponseStructure<String>> catchMailFailedException(MailFailedToSentException exception) {
		ResponseStructure<String> structure =new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("BAD REQUEST");
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
}
