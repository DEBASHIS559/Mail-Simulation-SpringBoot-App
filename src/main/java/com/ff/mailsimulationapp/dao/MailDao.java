package com.ff.mailsimulationapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.mailsimulationapp.entity.Mail;
import com.ff.mailsimulationapp.repository.MailRepository;

@Repository
public class MailDao {

	
	@Autowired
	private MailRepository mailRepository;
	
	public Mail createMail(Mail mail) {
		return mailRepository.save(mail);
	}
	
	public List<Mail> getMailByToUser(int touserid) {
		List<Mail> fromUser = mailRepository.findAllByUserInToUsersId(touserid);
		
		return fromUser;
	}
}
