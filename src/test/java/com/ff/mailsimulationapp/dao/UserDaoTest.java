package com.ff.mailsimulationapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ff.mailsimulationapp.entity.User;

@SpringBootTest
class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	void createUserTest() {
		User u = new User();
		u.setEmail("debu321@gmail.com1");
		u.setQuestion("Who am I?");
		u.setAnswer("victim");
		u.setPassword("hbe@ned");
		u.setRecoveryEmail("deb33@gmail.com");
		u.setUserName("Debu887");

		User receivedUser = userDao.createUser(u);

		assertNotNull(receivedUser.getId());
		assertEquals("debu321@gmail.com1", receivedUser.getEmail());
		assertEquals("Who am I?", receivedUser.getQuestion());
		assertEquals("victim", receivedUser.getAnswer());
		assertEquals("hbe@ned", receivedUser.getPassword());
		assertEquals("deb33@gmail.com", receivedUser.getRecoveryEmail());
		assertEquals("Debu887", receivedUser.getUserName());

	}

	@Test
	void findUserTest() {
		String email = "debu321@gmail.com1";
		String password = "hbe@ned";

		User receivedUser = userDao.findUser(email, password);

		assertNotNull(receivedUser.getId());
		assertEquals("debu321@gmail.com1", receivedUser.getEmail());
		assertEquals("Who am I?", receivedUser.getQuestion());
		assertEquals("victim", receivedUser.getAnswer());
		assertEquals("hbe@ned", receivedUser.getPassword());
		assertEquals("deb33@gmail.com", receivedUser.getRecoveryEmail());
		assertEquals("Debu887", receivedUser.getUserName());
	}

	@Test
	void findUserById() {
		int id = 1;
		User receivedUser = userDao.findUserById(id);

		assertNotNull(receivedUser.getId());
		assertEquals("debu321@gmail.com1", receivedUser.getEmail());
		assertEquals("Who am I?", receivedUser.getQuestion());
		assertEquals("victim", receivedUser.getAnswer());
		assertEquals("hbe@ned", receivedUser.getPassword());
		assertEquals("deb33@gmail.com", receivedUser.getRecoveryEmail());
		assertEquals("Debu887", receivedUser.getUserName());
	}

	@Test
	void findUserByEmail() {
		String email = "debu321@gmail.com1";
		User receivedUser = userDao.getUserByEmail(email);

		assertNotNull(receivedUser.getId());
		assertEquals("debu321@gmail.com1", receivedUser.getEmail());
		assertEquals("Who am I?", receivedUser.getQuestion());
		assertEquals("victim", receivedUser.getAnswer());
		assertEquals("hbe@ned", receivedUser.getPassword());
		assertEquals("deb33@gmail.com", receivedUser.getRecoveryEmail());
		assertEquals("Debu887", receivedUser.getUserName());
	}

}
