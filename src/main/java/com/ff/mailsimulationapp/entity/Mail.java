package com.ff.mailsimulationapp.entity;

import java.util.List;

import com.ff.mailsimulationapp.util.MailStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Mail {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	
	@OneToOne
	private User fromUser;
	 
	@OneToMany
	private List<User> toUsers;
	
	
	private String subject;
	private String message;
	
	@Enumerated(EnumType.STRING)
	private MailStatus status;
	
}
