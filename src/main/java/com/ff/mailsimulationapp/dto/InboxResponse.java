package com.ff.mailsimulationapp.dto;

import com.ff.mailsimulationapp.util.MailStatus;

import lombok.Data;

@Data
public class InboxResponse {
	
	
	int mailId;
	String fromUser;
	String subject;
	String message;
	MailStatus status;
	

}
