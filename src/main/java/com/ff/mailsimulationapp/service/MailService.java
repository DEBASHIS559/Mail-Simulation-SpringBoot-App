package com.ff.mailsimulationapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.mailsimulationapp.dao.MailDao;
import com.ff.mailsimulationapp.dao.UserDao;
import com.ff.mailsimulationapp.dto.MailDto;
import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.entity.Mail;
import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.exception.MailFailedToSentException;
import com.ff.mailsimulationapp.util.MailStatus;

@Service
public class MailService {
	
	@Autowired
	private MailDao mailDao;
	
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<String>> sendMail(MailDto mailDto){
		
		Mail mail=new Mail();
		
		User fromUser = userDao.getUserByEmail(mailDto.getFromUser());
		mail.setFromUser(fromUser);
		
		User toUser = userDao.getUserByEmail(mailDto.getToUsers());
		List<User> users = mail.getToUsers();
		users.add(toUser);
		
		
		if(toUser == null) {
			throw new MailFailedToSentException();
		}
		
		mail.setToUsers(users);
		
		mail.setStatus(MailStatus.SENT);
		mail.setMessage(mailDto.getMessage());
		mail.setSubject(mailDto.getSubject());
		
		
		mailDao.createMail(mail);
		
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		structure.setData("Mail sent successfully");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Mail>>> viewInbox(String email){
		
		User toUser = userDao.getUserByEmail(email);
			
		List<Mail> inbox = mailDao.getMailByToUser(toUser.getId());
		
		ResponseStructure<List<Mail>> structure=new ResponseStructure<List<Mail>>();
		structure.setData(inbox);
		structure.setMessage("Your inbox");
		structure.setStatusCode(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<List<Mail>>>(structure,HttpStatus.FOUND);
	}
}
