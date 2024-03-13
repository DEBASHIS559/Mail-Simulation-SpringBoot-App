package com.ff.mailsimulationapp.entity;

import java.util.ArrayList;
import java.util.List;

import com.ff.mailsimulationapp.util.MailStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Mail {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private User fromUser;
	 
	@ManyToMany
	
	private List<User> toUsers=new ArrayList<User>();
	
	@NotNull
	@Size(max = 30)
	private String subject;
	private String message="";
	
	@Enumerated(EnumType.STRING)
	private MailStatus status; 
}
