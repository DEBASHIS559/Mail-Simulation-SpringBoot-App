package com.ff.mailsimulationapp.dto;

import com.ff.mailsimulationapp.util.MailStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MailDto {
	private int id;
	
	private String fromUser;
	 
	
	private String toUsers;
	
	@NotNull
	@Size(max = 30)
	private String subject;
	@NotNull
	private String message="";
	
	@Enumerated(EnumType.STRING)
	private MailStatus status;
	
	
	private UserDto userDto;
}
