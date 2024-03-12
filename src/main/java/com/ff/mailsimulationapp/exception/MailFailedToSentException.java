package com.ff.mailsimulationapp.exception;

public class MailFailedToSentException extends RuntimeException{
	String message="Mail Delivery Failed";

	public MailFailedToSentException() {
		
	}

	public MailFailedToSentException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
