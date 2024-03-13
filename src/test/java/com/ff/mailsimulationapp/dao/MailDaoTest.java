package com.ff.mailsimulationapp.dao;

import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ff.mailsimulationapp.entity.Mail;
import com.ff.mailsimulationapp.entity.User;
import com.ff.mailsimulationapp.repository.MailRepository;
import com.ff.mailsimulationapp.util.MailStatus;

@SpringBootTest
class MailDaoTest {

	@Autowired
	private MailDao mailDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private MailRepository mailRepository;

	/*
	 * to test createMailTest() need to uncomment the @Test
	 */
//	@Test
	void createMailTest() {
		User user1 = userDao.findUserById(1);
		User user2 = userDao.findUserById(2);

		Mail mail = new Mail();
		mail.setFromUser(user1);
		mail.setToUsers(Arrays.asList(user1, user2));
		mail.setMessage("leave");
		mail.setSubject("Regarding leave");
		mail.setStatus(MailStatus.SENT);

		Mail save = mailDao.createMail(mail);
		assertNotNull(save);
		assertEquals(save, mail);
	}

	@Test
	void getMailByToUserTest() {
		List<Mail> toUserList = mailDao.getMailByToUser(1);

		assertNotNull(toUserList);
	}

	@Test
	void getMailByIdTest() {
		Mail mail = mailDao.getMailbyid(452);

		assertNotNull(mail);
	}

	@Test
	void getMailByFromUserTest() {
		User user = userDao.findUserById(1);
		List<Mail> mailList = mailDao.getMailByFromUser(user, MailStatus.SENT);

		assertNotNull(mailList);
		assertThatList(mailList);
	}

	@Test
	void getMailTest() {
		User user = userDao.findUserById(1);
		Mail mail = mailDao.getMail(user, MailStatus.SENT, 452);

		assertNotNull(mail);
	}

	/*
	 * to test deleteMailsByIdTest() need to uncomment the @Test and give different
	 * mail Id for every execution
	 */
//	@Test
	void deleteMailsByIdTest() {
		Mail mail = mailRepository.findById(402).orElse(null);
		String deleteMail = mailDao.deleteMailsById(mail);

		assertEquals("Deleted successfully", deleteMail);

	}
}
