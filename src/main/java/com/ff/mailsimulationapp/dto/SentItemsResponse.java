package com.ff.mailsimulationapp.dto;

import java.util.ArrayList;
import java.util.List;

import com.ff.mailsimulationapp.util.MailStatus;

import lombok.Data;

@Data
public class SentItemsResponse {
	
	
	int mailId;
	
	String subject;
	String message;
	MailStatus status;
	List<String> toUsers=new ArrayList<String>();

}
