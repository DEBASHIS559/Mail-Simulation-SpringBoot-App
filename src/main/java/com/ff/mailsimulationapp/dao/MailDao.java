package com.ff.mailsimulationapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.mailsimulationapp.entity.Mail;
import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.repository.MailRepository;
import com.ff.mailsimulationapp.util.MailStatus;

@Repository
public class MailDao {

	@Autowired
	private MailRepository mailRepository;

	public Mail createMail(Mail mail) {
		return mailRepository.save(mail);
	}

	public List<Mail> getMailByToUser(int touserid) {
		List<Mail> toUserlist = mailRepository.findAllByUserInToUsersId(touserid);

		return toUserlist;
	}

	public Mail getMailById(int mailid) {
		return mailRepository.findById(mailid).orElse(null);
	}

	public List<Mail> getMailByFromUser(User fromuser, MailStatus status) {
		List<Mail> fromUserlist = mailRepository.findByFromUserAndStatus(fromuser, status);
		return fromUserlist;
	}

	public Mail getMail(User fromuser, MailStatus status, int mailid) {
		return mailRepository.findByFromUserAndStatusAndId(fromuser, status, mailid).orElse(null);
	}
}
