package com.ff.mailsimulationapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.mailsimulationapp.dao.MailDao;
import com.ff.mailsimulationapp.dao.UserDao;
import com.ff.mailsimulationapp.dto.InboxResponse;
import com.ff.mailsimulationapp.dto.MailDto;
import com.ff.mailsimulationapp.dto.ResponseStructure;
import com.ff.mailsimulationapp.dto.SentItemsResponse;
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

	@Autowired
	private EmailSenderService mailSenderService;

	public ResponseEntity<ResponseStructure<String>> sendMail(MailDto mailDto) {

		Mail mail = new Mail();

		User fromUser = userDao.getUserByEmail(mailDto.getFromUser());
		mail.setFromUser(fromUser);

		if (mailDto.getStatus().equals(MailStatus.SENT)) {

			if (mailDto.getToUsers() != null) {
				User toUser = userDao.getUserByEmail(mailDto.getToUsers());

				List<User> users = mail.getToUsers();
				users.add(toUser);

				if (toUser == null) {

					String subject = "Mail Not Sent";
					String body = "Email id not found, trying sending with valid email.";

					mailSenderService.sendEmail(fromUser.getEmail(), subject, body);

					throw new MailFailedToSentException("Email not found!");

				}
				mail.setToUsers(users);

			} else {

				throw new MailFailedToSentException("Please specify the receiver mail id");

			}

		}
		mail.setStatus(mailDto.getStatus());
		mail.setMessage(mailDto.getMessage());
		mail.setSubject(mailDto.getSubject());

		mailDao.createMail(mail);

		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		structure.setData("Mail sent successfully");

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<InboxResponse>>> viewInbox(String email) {

		User toUser = userDao.getUserByEmail(email);

		List<Mail> inbox = mailDao.getMailByToUser(toUser.getId());

		List<InboxResponse> inboxes = new ArrayList<InboxResponse>();

		for (Mail mail : inbox) {

			InboxResponse m = new InboxResponse();
			m.setMailId(mail.getId());
			m.setFromUser(mail.getFromUser().getEmail());
			m.setStatus(mail.getStatus());
			m.setMessage(mail.getMessage());
			m.setSubject(mail.getSubject());

			inboxes.add(m);

		}

		ResponseStructure<List<InboxResponse>> structure = new ResponseStructure<List<InboxResponse>>();

		structure.setData(inboxes);
		structure.setMessage("Your inbox");
		structure.setStatusCode(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<List<InboxResponse>>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Mail>>> deleteMailsbyid(List<Integer> mailids) {

		for (Integer i : mailids) {
			Mail m = mailDao.getMailbyid(i);
			m.setToUsers(null);

			mailDao.deleteMailsById(m);
		}

		ResponseStructure<List<Mail>> structure = new ResponseStructure<List<Mail>>();
		structure.setData(null);
		structure.setMessage("Successfully deleted");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());

		return new ResponseEntity<ResponseStructure<List<Mail>>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<List<SentItemsResponse>>> viewSentItem(String email) {
		User fromUser = userDao.getUserByEmail(email);

		List<Mail> sentItems = mailDao.getMailByFromUser(fromUser, MailStatus.SENT);

		List<SentItemsResponse> responses = new ArrayList<SentItemsResponse>();

		for (Mail mail : sentItems) {
			SentItemsResponse s = new SentItemsResponse();
			s.setMailId(mail.getId());
			s.setMessage(mail.getMessage());
			s.setStatus(mail.getStatus());
			s.setSubject(mail.getSubject());
			List<String> responseList = s.getToUsers();
			List<User> toUsers = mail.getToUsers();
			for (User user : toUsers) {
				responseList.add(user.getEmail());

			}
			s.setToUsers(responseList);

			responses.add(s);

		}

		ResponseStructure<List<SentItemsResponse>> structure = new ResponseStructure<List<SentItemsResponse>>();
		structure.setData(responses);
		structure.setMessage("Your Sent Items");
		structure.setStatusCode(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<List<SentItemsResponse>>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<InboxResponse>>> viewDraftItem(String email) {
		User fromUser = userDao.getUserByEmail(email);

		List<Mail> draftItems = mailDao.getMailByFromUser(fromUser, MailStatus.DRAFT);

		List<InboxResponse> drafts = new ArrayList<InboxResponse>();
		for (Mail mail : draftItems) {

			InboxResponse m = new InboxResponse();
			m.setMailId(mail.getId());
			m.setFromUser(mail.getFromUser().getEmail());
			m.setStatus(mail.getStatus());
			m.setMessage(mail.getMessage());
			m.setSubject(mail.getSubject());

			drafts.add(m);

		}
		ResponseStructure<List<InboxResponse>> structure = new ResponseStructure<List<InboxResponse>>();
		structure.setData(drafts);
		structure.setMessage("Your Draft Items");
		structure.setStatusCode(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<List<InboxResponse>>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<String>> editDraftMail(String email, int mailid, MailDto mailDto) {
		User fromUser = userDao.getUserByEmail(email);

		Mail mail = mailDao.getMail(fromUser, MailStatus.DRAFT, mailid);
		System.err.println(mail);

		if (mailDto.getToUsers() != null) {

			User toUser = userDao.getUserByEmail(mailDto.getToUsers());
			List<User> mailList = mail.getToUsers();
			mailList.add(toUser);

			mail.setToUsers(mailList);
			mail.setStatus(MailStatus.SENT);
		}
		if (mailDto.getMessage() != null) {
			mail.setMessage(mailDto.getMessage());
		}
//		if(mailDto.getStatus()!= null) {
//			mail.setStatus(mailDto.getStatus());
//		}
		if (mailDto.getSubject() != null) {
			mail.setSubject(mailDto.getSubject());
		}

		mailDao.createMail(mail);

		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setData("Your Draft mail is updated");
		structure.setMessage(HttpStatus.OK.getReasonPhrase());
		structure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}
}
